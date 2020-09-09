package com.smtm.mvvm.presentation.common.pageload

data class PageLoadConfiguration(
    val startPageNumber: Int,
    val maxPageNumber: Int,
    val requiredDataSize: Int,
    val preloadAllowLineMultiple: Int = 1
)