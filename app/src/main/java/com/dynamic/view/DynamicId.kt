package com.dynamic.view

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Annotation to create the class ViewHolder
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class DynamicId(val id: String = "")
