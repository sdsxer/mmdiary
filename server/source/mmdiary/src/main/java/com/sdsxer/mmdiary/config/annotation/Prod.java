package com.sdsxer.mmdiary.config.annotation;

import com.sdsxer.mmdiary.common.Constants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Profile;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Profile(Constants.CONFIG_ENV_PROD)
public @interface Prod {

}
