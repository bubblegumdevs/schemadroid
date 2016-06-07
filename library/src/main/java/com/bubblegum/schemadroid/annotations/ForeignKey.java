package com.bubblegum.schemadroid.annotations;

import com.bubblegum.schemadroid.model.Tabbable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKey {

    Class<? extends Tabbable> references();

}
