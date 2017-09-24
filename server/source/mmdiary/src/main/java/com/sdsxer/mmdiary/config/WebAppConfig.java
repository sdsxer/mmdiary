package com.sdsxer.mmdiary.config;

import com.google.common.base.Strings;
import com.sdsxer.mmdiary.utils.TokenManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by leon on 2017/9/24.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private TokenManager tokenManager;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new TokenValidationInterceptor()).addPathPatterns("/**");
    super.addInterceptors(registry);
  }

  class TokenValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler)
        throws Exception {

      // 是否登录请求
      String requestServletPath = request.getServletPath();
      if(!Strings.isNullOrEmpty(requestServletPath)
          && requestServletPath.equalsIgnoreCase("/user/login")) {
        return true;
      }

      // 验证Token是否有效
      String token = request.getHeader("token");

      if(token == null || token.isEmpty()) {
        return true;
      }


//      response.sendRedirect(request.getContextPath() + loginUrl);



      return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {

    }
  }
}
