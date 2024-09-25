package com.tejas.mvvm.test.domain.base

interface BaseUseCaseWithParams< P, R > {
    suspend fun run(params : P) : R
}