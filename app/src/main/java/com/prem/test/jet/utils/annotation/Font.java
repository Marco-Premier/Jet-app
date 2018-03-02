package com.prem.test.jet.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by prem on 01/03/2018.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Font {

    enum Fonts {
        ROMAN, BOOK, LIGHT
    }

    Fonts value() default Fonts.ROMAN;

}

