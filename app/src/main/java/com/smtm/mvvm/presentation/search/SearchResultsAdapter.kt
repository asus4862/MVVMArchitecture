package com.smtm.mvvm.presentation.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.databinding.ItemRepositoryBinding
import com.smtm.mvvm.databinding.ItemRetryFooterBinding
import com.smtm.mvvm.extension.cancelImageLoad
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 */
class SearchResultsAdapter : ListAdapter<UserDocument, RecyclerView.ViewHolder>(DiffCallback()) {
    companion object {
        private const val TYPE_ITEM = 1
        private const val TYPE_FOOTER = 2
    }

    private var retryFooterViewVisibility = false

    private val _footerClicks = PublishSubject.create<Unit>()
    val footerClicks: Observable<Unit> = _footerClicks

    var onBindPosition: ((position: Int) -> Unit)? = null



    private val _itemClicks = PublishSubject.create<UserDocument>()
    val itemClicks: Observable<UserDocument> = _itemClicks


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_ITEM) {
            SearchResultsViewHolder(ItemRepositoryBinding.inflate(inflater, parent, false)).apply {
                binding.repositoryItemRemove.setOnClickListener {
                    _itemClicks.onNext(getItem(adapterPosition))
                }
            }
        } else {
            RetryFooterViewHolder(ItemRetryFooterBinding.inflate(inflater, parent, false)).apply {
                binding.retryButton.setOnClickListener {
                    _footerClicks.onNext(Unit)
                }
            }
        }
//
//
//        return SearchResultsViewHolder(
//            ItemRepositoryBinding.inflate(inflater, parent, false)).apply {
//            binding.repositoryItemRemove.setOnClickListener {
//               )
//                _itemClicks.onNext(getItem(adapterPosition))
//            }
//        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SearchResultsViewHolder -> {
                Log.e("gg","onBindViewHolder")
                holder.setItem(getItem(position))
                onBindPosition?.invoke(position)
            }
            is RetryFooterViewHolder -> {
                holder.setRetryVisibility(retryFooterViewVisibility)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isFooterViewPosition(position)) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    /**
     * recyclerview adapter는 row가 재활용이 되기 위해 내부데이터를 지우기 바로 전에 onViewRecycled를 호출하는데요
    출처: https://trend21c.tistory.com/2021 [나를 찾는 아이]
     샘플 코드 https://github.com/spotlight21c/viewpagerinrecyclerview
     */
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when(holder) {
            is SearchResultsViewHolder -> {
                holder.clear()
            }
        }
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        return if (itemCount == 0) {
            itemCount
        } else {
            itemCount + 1
        }
    }

    fun isFooterViewPosition(position: Int): Boolean {
        return position == super.getItemCount()
    }

    fun changeFooterViewVisibility(visibility: Boolean) {
        retryFooterViewVisibility = visibility
        notifyItemChanged(super.getItemCount())
    }


    class SearchResultsViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(userDocument: UserDocument) {
            binding.userDocument = userDocument
            binding.executePendingBindings()
        }

        fun clear() {
            binding.repositoryItemAvatarUrl.cancelImageLoad()
        }
    }

    class RetryFooterViewHolder(
        val binding: ItemRetryFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setRetryVisibility(visible: Boolean) {
            binding.retryButtonVisibility = visible
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserDocument>() {
        override fun areItemsTheSame(oldItem: UserDocument, newItem: UserDocument): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserDocument, newItem: UserDocument): Boolean {
            return oldItem == newItem
        }
    }
}