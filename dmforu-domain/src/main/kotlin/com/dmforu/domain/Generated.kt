package com.dmforu.domain

import java.lang.annotation.Documented
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.annotation.AnnotationTarget.FUNCTION

@Documented
@Retention(RUNTIME)
@Target(TYPE, FUNCTION)
annotation class Generated