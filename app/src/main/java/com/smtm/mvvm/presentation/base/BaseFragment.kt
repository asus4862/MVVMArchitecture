package com.smtm.mvvm.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 */
abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return DataBindingUtil.inflate<B>(inflater, getLayoutId(), container, false).apply {
            binding = this
//            lifecycleOwner = this@BaseFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
    }


    protected fun showToast(message: String?) {
        message?.let { Toast.makeText(activity?.applicationContext, it, Toast.LENGTH_SHORT).show() }
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

}