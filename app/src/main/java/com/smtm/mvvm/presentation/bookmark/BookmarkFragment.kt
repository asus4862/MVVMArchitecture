package com.smtm.mvvm.presentation.bookmark

import android.os.Bundle
import android.util.Log
import com.smtm.mvvm.R
import com.smtm.mvvm.presentation.base.RxBaseFragment
import com.smtm.mvvm.databinding.FragmentBookmarkBinding
import com.smtm.mvvm.extension.throttleFirstWithHalfSecond
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 */
class BookmarkFragment : RxBaseFragment<FragmentBookmarkBinding>() {


    private val bookmarkViewModel: BookmarkViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_bookmark
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        binding.bookmarkListViewModel = bookmarkViewModel
//        binding.setLifecycleOwner(this)
        // TODO: Use the ViewModel
        Log.e("gg","BookmarkFragment onActivityCreated()")
        initFavoriteListViewModel()
        initFavoriteRecyclerView()
        binding.bookmarkListViewModel = bookmarkViewModel
    }

    private fun initFavoriteListViewModel() {
        with(bookmarkViewModel) {
            bookmarkViewModel.loadFavoriteImageList()
        }
    }


    private fun initFavoriteRecyclerView() {
        binding.bookmarkRecyclerView.apply {
            adapter = BookmarkAdapter().apply {
                itemClicks.throttleFirstWithHalfSecond()
                    .subscribe { userDocument -> bookmarkViewModel.onClickDel(userDocument) }
                    .disposeByOnDestroy()
            }
        }
    }
}


