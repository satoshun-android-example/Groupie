package com.github.satoshun.example.builder

import androidx.annotation.LayoutRes
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

fun groupieAdapter(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply(block)

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>() {
  operator fun Group.unaryPlus() {
    add(this)
  }

  fun item(
    @LayoutRes layoutRes: Int,
    bind: GroupieViewHolder.(Int) -> Unit
  ) {
    add(BuilderItem(layoutRes, bind))
  }

  fun expandable(
    expandableItem: Group,
    block: ExpandableGroup.() -> Unit
  ) {
    add(ExpandableGroup(expandableItem).apply(block))
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
