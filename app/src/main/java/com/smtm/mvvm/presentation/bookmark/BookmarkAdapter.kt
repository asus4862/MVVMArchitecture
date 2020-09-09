package com.smtm.mvvm.presentation.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smtm.mvvm.data.repository.user.model.UserDocument
import com.smtm.mvvm.databinding.ItemBookmarkBinding
import com.smtm.mvvm.extension.cancelImageLoad
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 */
class BookmarkAdapter : ListAdapter<UserDocument, BookmarkAdapter.BookmarkViewHolder>(DiffCallback()) {

    private val _itemClicks = PublishSubject.create<UserDocument>()
    val itemClicks: Observable<UserDocument> = _itemClicks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BookmarkViewHolder(ItemBookmarkBinding.inflate(inflater, parent, false)).apply {
            binding.bookmarkItemDel.setOnClickListener {

                _itemClicks.onNext(getItem(adapterPosition))
            }
        }

    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    override fun onViewRecycled(holder: BookmarkViewHolder) {
        holder.clear()
        super.onViewRecycled(holder)
    }



    class BookmarkViewHolder(
        val binding: ItemBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(imageDocument: UserDocument) {
//            binding.userDocument = imageDocument
//            binding.executePendingBindings()
        }

        fun clear() {
            binding.bookmarkItemAvatarUrl.cancelImageLoad()
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



