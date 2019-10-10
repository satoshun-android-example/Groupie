package com.github.satoshun.example.sticky

import android.view.View
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.HeaderItemBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainAdapter : GroupAdapter<GroupieViewHolder>(),
  StickyHeaderItemDecoration.StickyHeaderInterface {
  init {
    update(
      (0 until 100).map {
        if (it % 10 == 0) HeaderItem()
        else MainItem()
      }
    )
  }

  override fun getHeaderPositionForItem(itemPosition: Int): Int =
    (itemPosition downTo 0)
      .firstOrNull { isHeader(it) } ?: -1

  override fun getHeaderLayout(headerPosition: Int): Int {
    return R.layout.header_item
  }

  override fun bindHeaderData(header: View, headerPosition: Int) {
    val headerItem = getHeaderItem(headerPosition)
    headerItem?.bind(HeaderItemBinding.bind(header), headerPosition)
  }

  override fun isHeader(itemPosition: Int): Boolean {
    return getItem(itemPosition) is HeaderItem
  }

  private fun getHeaderItem(position: Int): HeaderItem? = getItem(position) as? HeaderItem
}

class MainItem : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(viewBinding: MainItemBinding, position: Int) {
  }
}

class HeaderItem : BindableItem<HeaderItemBinding>() {
  override fun getLayout(): Int = R.layout.header_item

  override fun bind(viewBinding: HeaderItemBinding, position: Int) {
    bindHeader(viewBinding, position)
  }

  companion object {
    fun bindHeader(binding: HeaderItemBinding, data: Int) {
      binding.title.text = data.toString()
    }
  }
}
