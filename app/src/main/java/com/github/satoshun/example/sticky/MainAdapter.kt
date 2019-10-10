package com.github.satoshun.example.sticky

import android.view.View
import android.widget.TextView
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.HeaderItemBinding
import com.github.satoshun.example.databinding.MainItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainAdapter : GroupAdapter<GroupieViewHolder>(),
  StickHeaderItemDecoration.StickyHeaderInterface {
  init {
    update(
      (0..100).map {
        if (it % 10 == 0) HeaderItem()
        else MainItem()
      }
    )
  }

  override fun getHeaderPositionForItem(itemPosition: Int): Int =
    (itemPosition downTo 0)
      .firstOrNull { getItem(it) is HeaderItem } ?: -1

  override fun getHeaderLayout(headerPosition: Int): Int {
    return R.layout.header_item
  }

  override fun bindHeaderData(header: View, headerPosition: Int) {
    header.findViewById<TextView>(R.id.title).text = headerPosition.toString()
  }

  override fun isHeader(itemPosition: Int): Boolean {
    return getItem(itemPosition) is HeaderItem
  }
}

class MainItem : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(viewBinding: MainItemBinding, position: Int) {
  }
}

class HeaderItem : BindableItem<HeaderItemBinding>() {
  override fun getLayout(): Int = R.layout.header_item

  override fun bind(viewBinding: HeaderItemBinding, position: Int) {
    viewBinding.title.text = position.toString()
  }
}
