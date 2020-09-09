package com.smtm.mvvm.presentation.main

import android.content.Context
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.smtm.mvvm.R
import com.smtm.mvvm.presentation.bookmark.BookmarkFragment
import com.smtm.mvvm.presentation.search.SearchFragment
import com.smtm.mvvm.presentation.webview.WebFragment

class MainAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
    )

    // fragNum : 몇개의 Fragment를 생성할 것인지를 인자로 받는다.
    // fm : FragmentManager로 FragmentStatePagerAdapter의 생성자 매개변수

        val firstFrag : Fragment = SearchFragment()
//    val firstFrag: Fragment = WebFragment()
    val secondFrag: Fragment = BookmarkFragment()

    // position 별로 어느 Fragment로 이동할지 결정
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> firstFrag
            1 -> secondFrag
            else -> firstFrag
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    // 몇 개의 Fragment인지 결정
    override fun getCount(): Int = 2
}