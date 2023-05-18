package de.olegrom.starwars

import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun platformModule(): Module
expect fun getPlatform(): Platform

/**
 *
 * Common parcelable implementation for android
 */

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()

expect interface CommonParcelable