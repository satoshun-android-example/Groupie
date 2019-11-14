package com.github.satoshun.example.builder

import androidx.annotation.LayoutRes
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

fun groupieAdapter(body: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply(body)

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>() {
  operator fun Group.unaryPlus() {
    add(this)
  }

  fun item(
    @LayoutRes layoutRes: Int,
    bind: GroupieViewHolder.(Int) -> Unit
  ) {
    BuilderItem(layoutRes, bind)
  }
}

class BuilderItem<T : GroupieViewHolder>(
  @LayoutRes private val layoutRes: Int,
  private val bind: T.(Int) -> Unit
) : Item<T>() {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: T, position: Int) {
    viewHolder.bind(position)
  }
}
