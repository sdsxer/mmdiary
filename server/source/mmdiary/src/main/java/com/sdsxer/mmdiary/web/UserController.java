package com.sdsxer.mmdiary.web;

import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.common.ErrorCode;
import com.sdsxer.mmdiary.domain.User;
import com.sdsxer.mmdiary.service.UserService;
import com.sdsxer.mmdiary.storage.AvatarStorageService;
import com.sdsxer.mmdiary.storage.StorageException;
import com.sdsxer.mmdiary.utils.AccountValidatorUtils;
import com.sdsxer.mmdiary.utils.FileUtils;
import com.sdsxer.mmdiary.utils.ImageUtils;
import com.sdsxer.mmdiary.utils.TokenManager;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.FailureResponse;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import com.sdsxer.mmdiary.web.response.user.EditProfileResponse;
import com.sdsxer.mmdiary.web.response.user.LoginResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

  private static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private AvatarStorageService storageService;

  /**
   * user login
   * @param mobile
   * @param password
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public BaseResponse login(@RequestParam(value = "mobile") String mobile,
      @RequestParam(value = "password") String password) {

    BaseResponse loginResponse;

    // check mobile and password's format
    if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
      loginResponse = new FailureResponse(ErrorCode.User.EMPTY_MOBILE_OR_PASSWORD);
      return loginResponse;
    }

    // check mobile's format
    if(!AccountValidatorUtils.isMobile(mobile)) {
      loginResponse = new FailureResponse(ErrorCode.User.ILLEGAL_MOBILE);
      return loginResponse;
    }

    // check password's format
    if(AccountValidatorUtils.isPassword(password)) {
      loginResponse = new FailureResponse(ErrorCode.User.ILLEGAl_PASSWORD);
      return loginResponse;
    }

    // retrieve user
    User user = userService.login(mobile);

    // check user's existence
    if(user == null) {
      loginResponse = new FailureResponse(ErrorCode.User.USER_NOT_EXIST);
      return loginResponse;
    }

    // check password's correction
    if(!StringUtils.equals(user.getPassword(), password)) {
      loginResponse = new FailureResponse(ErrorCode.User.PASSWORD_NOT_CORRECT);
      return loginResponse;
    }

    // save token, if user already have one, then it will be replaced by the new one
    String token = tokenManager.createToken(user.getId());
    if(StringUtils.isEmpty(token)) {
      loginResponse = new FailureResponse(ErrorCode.User.UNABLE_CREATE_TOKEN);
      return loginResponse;
    }

    // encapsulate response
    loginResponse = new LoginResponse(token, user);
    return loginResponse;
  }

  /**
   * user logout
   * @param token
   * @return
   */
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public BaseResponse logout(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token) {

    BaseResponse response = null;

    // delete token from pool
    tokenManager.deleteToken(token);

    // encapsulate response
    response = new SuccessResponse();
    return response;
  }

  /**
   * user edit profile
   * @param token
   * @param name
   * @param gender
   * @param avatar
   * @return
   */
  @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
  public BaseResponse editProfile(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token,
      @RequestParam("name") String name, @RequestParam("gender") int gender,
      @RequestParam(value = "avatar", required = false) MultipartFile avatar) {

    BaseResponse response = null;

    // check user's existence
    User user = userService.findUser(tokenManager.getUserId(token));
    if(user == null) {
      response = new FailureResponse(ErrorCode.User.USER_NOT_EXIST);
      return response;
    }

    // check param's legality
    if(StringUtils.isEmpty(name)) { // name
      response = new FailureResponse(ErrorCode.User.EMPTY_NAME);
      return response;
    }
    if(gender < 0 || gender > 2) { // gender
      response = new FailureResponse(ErrorCode.User.ILLEGAL_GENDER);
      return response;
    }

    // save params
    user.setName(name);
    user.setGender(gender);

    // save old avatar path
    String originalAvatarPath = user.getAvatarUrl();
    boolean avatarUpdated = false;

    // save avatar
    if(avatar != null) {
      // check image format
      if(!ImageUtils.isFormatSupported(FileUtils.getFileSuffix(avatar.getOriginalFilename()))) {
        response = new FailureResponse(ErrorCode.Common.UNSUPPORTED_IMAGE_FORMAT);
        return response;
      }
      // check image size
      try {
        if(!ImageUtils.isImageSizeMatch(Constants.AVATAR_WIDTH,
            Constants.AVATAR_HEIGHT, avatar.getInputStream())) {
          response = new FailureResponse(ErrorCode.Common.UNSUPPORTED_IMAGE_SIZE);
          return response;
        }
      } catch (IOException e) {
        logger.warn("Couldn't resolve image size", e);
        response = new FailureResponse(ErrorCode.Common.UNSUPPORTED_IMAGE_SIZE);
        return response;
      }
      // save image
      Path relativePath = null;
      try {
        relativePath = storageService.storeAvatarImage(user.getId(), avatar);
      }
      catch (StorageException e) {
        logger.error("Could not save file", e);
      }
      if(relativePath == null) {
        response = new FailureResponse(ErrorCode.Common.UNABLE_SAVE_FILE);
        return response;
      }
      // save avatar path
      user.setAvatarUrl(relativePath.toString());
      avatarUpdated = true;
    }

    // update user info
    user = userService.updateUser(user);
    if(user == null) {
      response = new FailureResponse(ErrorCode.Common.OPERATION_EXCEPTION);
      return response;
    }

    // delete old avatar if exist
    if(avatarUpdated && !StringUtils.isEmpty(originalAvatarPath)) {
      try {
        Files.delete(Paths.get(storageService.getRootLocation()
            + File.separator + originalAvatarPath));
      } catch (IOException e) {
        logger.warn("Could not delete old avatar file", e);
      }
    }

    // encapsulate response
    response = new EditProfileResponse(user);
    return response;
  }

  /**
   * modify password
   * @param token
   * @param oldPassword md5 encrypted password
   * @param newPassword md5 encrypted password
   * @return
   */
  @RequestMapping(value = "/password/modify", method = RequestMethod.POST)
  public BaseResponse modifyPassword(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token,
      @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {

    BaseResponse response = null;

    // check token's validation
    if(!tokenManager.checkToken(token)) {
      response = new FailureResponse(ErrorCode.User.TOKEN_EXPIRED);
      return response;
    }

    // check old password's format
    if(StringUtils.isEmpty(oldPassword)) {
      response = new FailureResponse(ErrorCode.User.EMPTY_OLD_PASSWORD);
      return response;
    }
    if(!AccountValidatorUtils.isMD5_32(oldPassword)) {
      response = new FailureResponse(ErrorCode.User.ILLEGAL_OLD_PASSWORD);
      return response;
    }

    // check new password's format
    if(StringUtils.isEmpty(newPassword)) {
      response = new FailureResponse(ErrorCode.User.EMPTY_NEW_PASSWORD);
      return response;
    }
    if(!AccountValidatorUtils.isMD5_32(newPassword)) {
      response = new FailureResponse(ErrorCode.User.ILLEGAL_NEW_PASSWORD);
      return response;
    }

    // check user's existence
    User user = userService.findUser(tokenManager.getUserId(token));
    if(user == null) {
      response = new FailureResponse(ErrorCode.User.USER_NOT_EXIST);
      return response;
    }

    // check old password's correction
    if(!StringUtils.equals(user.getPassword(), oldPassword)) {
      response = new FailureResponse(ErrorCode.User.INCORRECT_OLD_PASSWORD);
      return response;
    }

    // update password
    user.setPassword(newPassword);

    // save to database
    user = userService.updateUser(user);
    if(user == null) {
      response = new FailureResponse(ErrorCode.Common.OPERATION_EXCEPTION);
      return response;
    }

    // encapsulate response
    response = new SuccessResponse();
    return response;
  }
}
