package com.tobidaada.community.utils

sealed class TestMode {
    object SuccessMode: TestMode()
    object FailureMode: TestMode()
}