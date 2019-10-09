package com.github.satoshun.example

import com.github.satoshun.example.databinding.MainItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainAdapter : GroupAdapter<GroupieViewHolder>() {
  init {
    update(
      (0..100).map { MainItem() }
    )
  }
}

class MainItem : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(viewBinding: MainItemBinding, position: Int) {
  }
}
