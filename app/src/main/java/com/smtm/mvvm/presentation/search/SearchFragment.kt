package com.smtm.mvvm.presentation.search


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.smtm.mvvm.R
import com.smtm.mvvm.presentation.base.RxBaseFragment
import com.smtm.mvvm.databinding.FragmentSearchBinding
import com.smtm.mvvm.extension.throttleFirstWithHalfSecond

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 */
class SearchFragment : RxBaseFragment<FragmentSearchBinding>() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("gg","SearchFragment onActivityCreated()!!")
        initImageRecyclerView()
        initImageListViewModel()
//        binding.searchListViewModel = searchViewModel
//        binding.setLifecycleOwner(this)
        // TODO: Use the ViewModel

    }


    private fun initImageRecyclerView() {
        binding.searchRecyclerView.apply {


            adapter = SearchResultsAdapter().apply {
                onBindPosition = { position ->
                    searchViewModel.loadMoreImagesIfPossible(position)
                }

                itemClicks.throttleFirstWithHalfSecond()
                    .subscribe { document -> searchViewModel.onClickFavorite(document) }
                    .disposeByOnDestroy()

                footerClicks.throttleFirstWithHalfSecond()
                    .subscribe { searchViewModel.retryLoadMoreImageList() }
                    .disposeByOnDestroy()
            }
        }
    }

    private fun initImageListViewModel() {
        val owner = this
        with(searchViewModel) {
            showMessageEvent.observe(owner, Observer { message ->
                showToast(message)
            })
        }
        binding.searchListViewModel = searchViewModel
    }
}