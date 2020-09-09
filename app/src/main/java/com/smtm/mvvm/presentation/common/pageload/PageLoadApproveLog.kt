package ccom.leopold.mvvm.common.pageload

data class PageLoadApproveLog<T>(
    var key: T? = null,
    var dataTotalSize: Int? = null,
    var pageNumber: Int? = null
)