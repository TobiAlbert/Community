package com.tobidaada.community.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOCoroutineDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainCoroutineDispatcher