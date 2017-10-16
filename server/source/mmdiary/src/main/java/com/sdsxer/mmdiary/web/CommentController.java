package com.sdsxer.mmdiary.web;


import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.common.ErrorCode;
import com.sdsxer.mmdiary.common.ErrorCode.Common;
import com.sdsxer.mmdiary.domain.Comment;
import com.sdsxer.mmdiary.service.CommentService;
import com.sdsxer.mmdiary.utils.TokenManager;
import com.sdsxer.mmdiary.web.response.BaseResponse;
import com.sdsxer.mmdiary.web.response.FailureResponse;
import com.sdsxer.mmdiary.web.response.SuccessResponse;
import com.sdsxer.mmdiary.web.response.comment.CreateCommentResponse;
import com.sdsxer.mmdiary.web.response.comment.RetrieveCommentListResponse;
import com.sdsxer.mmdiary.web.response.comment.UpdateCommentResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController extends BaseController {

  private static Logger logger = LoggerFactory.getLogger(CommentController.class);

  @Autowired
  private CommentService commentService;

  @Autowired
  private TokenManager tokenManager;

  @RequestMapping(value = "/comment/create", method = RequestMethod.POST)
  public BaseResponse createDiary(@RequestParam("content") String content) {
    BaseResponse response = null;

    // check param's legality
    if(StringUtils.isEmpty(content)) {
      response = new FailureResponse(ErrorCode.Comment.EMPTY_CONTENT);
      return response;
    }

    // create comment
    Comment comment = commentService.createComment(content);

    // operation exception
    if(comment == null) {
      response = new FailureResponse(Common.OPERATION_EXCEPTION);
      return response;
    }

    // encapsulate response
    response = new CreateCommentResponse(comment);
    return response;
  }


  @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
  public BaseResponse updateComment(@RequestParam(Constants.REQUEST_HEADER_TOKEN) String token,
  @RequestParam("id") long id, @RequestParam("content") String content) {
    BaseResponse response = null;

    // retrieve original comment
    Comment originalComment = commentService.retrieveComment(id);
    if(originalComment == null) {
      response = new FailureResponse(ErrorCode.Comment.COMMENT_NOT_EXIST);
      return response;
    }

    // check authority
    long userId = tokenManager.getUserId(token);
    if(originalComment.getUser() == null || originalComment.getUser().getId() != userId) {
      response = new FailureResponse(Common.UNAUTHORIZED);
      return response;
    }

    // check id's legality
    if(id <= 0) {
      response = new FailureResponse(Common.ILLEGAL_PARAM);
      return response;
    }

    // check params's legality
    if(StringUtils.isEmpty(content)) {
      response = new FailureResponse(Common.ILLEGAL_PARAM);
      return response;
    }

    // create comment
    Comment comment = commentService.createComment(content);

    // operation exception
    if(comment == null) {
      response = new FailureResponse(Common.OPERATION_EXCEPTION);
      return response;
    }

    // encapsulate response
    response = new UpdateCommentResponse(comment);
    return response;
  }


  @RequestMapping(value = "/comment/retrieve/list", method = RequestMethod.POST)
  public BaseResponse retrieveCommentList(@RequestParam("index") long index,
      @RequestParam("size") int size) {
    BaseResponse response = null;

    // check params's legality
    if(index < 0 || size <= 0) {
      response = new FailureResponse(Common.ILLEGAL_PARAM);
      return response;
    }

    // retrieve comment list
    Page<Comment> commentPage = commentService.retrieveCommentList(index, size);

    // encapsulate response
    response = new RetrieveCommentListResponse(commentPage);
    return response;
  }

  @RequestMapping(value = "/comment/delete", method = RequestMethod.POST)
  public BaseResponse deleteComment(@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token,
      @RequestParam("id") long id) {
    BaseResponse response = null;

    // check id's legality
    if(id <= 0) {
      response = new FailureResponse(Common.ILLEGAL_PARAM);
      return response;
    }

    Comment comment = commentService.retrieveComment(id);

    // check whether comment exist
    if(comment == null) {
      response = new FailureResponse(ErrorCode.Comment.COMMENT_NOT_EXIST);
      return response;
    }

    // only author and admin have authorize to delete comment
    long userId = tokenManager.getUserId(token);
    if(comment.getUser() == null || comment.getUser().getId() != userId) {
      response = new FailureResponse(ErrorCode.Common.UNAUTHORIZED);
      return response;
    }

    // delete diary
    commentService.deleteComment(id);

    // encapsulate response
    response = new SuccessResponse();
    return response;
  }
}
