package com.sdsxer.mmdiary.config;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.common.Constants;
import com.sdsxer.mmdiary.utils.TokenManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Interceptor config
 * Created by leon on 2017/9/24.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(WebAppConfig.class);

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private Environment environment;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // disable token validation interceptor under dev environment
    String[] profiles = environment.getActiveProfiles();
    if(!ArrayUtils.contains(profiles, Constants.CONFIG_ENV_DEV)) {
      registry.addInterceptor(new TokenValidationInterceptor()).addPathPatterns("/**");
    }
    super.addInterceptors(registry);
  }

  /**
   * Token validation interceptor
   */
  class TokenValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler)
        throws Exception {

      // login request, ignore
      String requestServletPath = request.getServletPath();
      if(!Strings.isNullOrEmpty(requestServletPath)
          && requestServletPath.equalsIgnoreCase("/user/login")) {
        return true;
      }

      // check token's validation
      String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
      boolean valid = tokenManager.checkToken(token);
      if(valid) { // valid token, ignore
        return true;
      }

      // send response
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
      // to do
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
      // to do
    }
  }
}
