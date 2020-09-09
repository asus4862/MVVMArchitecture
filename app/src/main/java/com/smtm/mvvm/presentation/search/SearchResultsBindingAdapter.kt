package com.smtm.mvvm.presentation.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 **/
@BindingAdapter("imageSearchState")
fun setImageSearchState(recyclerView: RecyclerView, searchState: SearchState) {
    (recyclerView.adapter as? SearchResultsAdapter)?.let { adapter ->
        with(adapter) {
            when (searchState) {
                SearchState.NONE, SearchState.SUCCESS -> {
                    changeFooterViewVisibility(false)
                }
                SearchState.FAIL -> {
                    changeFooterViewVisibility(true)
                }
            }
        }
    }
}