package com.github.satoshun.example.builder

import android.view.View
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
    block: View.(Int) -> Unit
  ) {
    add(BuilderItem(layoutRes, block))
  }

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(Int) -> Unit,
    expandableBlock: BuilderExpandableGroup.() -> Unit
  ) {
    add(BuilderExpandableGroup(BuilderItem(layoutRes, block)).apply(expandableBlock))
  }
}

class BuilderExpandableGroup(group: Group) : ExpandableGroup(group) {
  fun item(
    @LayoutRes layoutRes: Int,
    block: View.(Int) -> Unit
  ) {
    add(BuilderItem(layoutRes, block))
  }

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(Int) -> Unit,
    expandableBlock: BuilderExpandableGroup.() -> Unit
  ) {
    add(BuilderExpandableGroup(BuilderItem(layoutRes, block)).apply(expandableBlock))
  }
}

class BuilderItem(
  @LayoutRes private val layoutRes: Int,
  private val block: View.(Int) -> Unit
) : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(position)
  }
}
