package com.dmforu.domain

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@MustBeDocumented
@Retention(RUNTIME)
@Target(TYPE, FUNCTION, CLASS)
annotation class Generated