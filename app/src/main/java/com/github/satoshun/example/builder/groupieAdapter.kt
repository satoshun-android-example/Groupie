package com.github.satoshun.example.builder

import android.view.View
import androidx.annotation.LayoutRes
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import java.util.concurrent.atomic.AtomicLong

fun groupieAdapter(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply {
    block()
    addAll()
  }

private val ID_COUNTER = AtomicLong(0)

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>() {
  private var items: MutableList<Group> = mutableListOf()

  fun update(block: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
    BuilderGroupAdapter().apply {
      block()
      updateAll()
    }

  operator fun Group.unaryPlus() {
    items.plusAssign(this)
  }

  fun item(
    @LayoutRes layoutRes: Int,
    data: Any? = null,
    block: View.(Int) -> Unit
  ) {
    items.plusAssign(
      BuilderItem(
        data?.hashCode()?.toLong() ?: ID_COUNTER.decrementAndGet(),
        layoutRes,
        data,
        block
      )
    )
  }

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(Int, ExpandableGroup) -> Unit,
    expandedBlock: BuilderExpandableGroup.() -> Unit
  ) {
    items.plusAssign(
      BuilderExpandableGroup(ExpandableBuilderItem(layoutRes, block)).apply(
        expandedBlock
      )
    )
  }

  internal fun addAll() {
    addAll(items)
    items.clear()
  }

  private fun updateAll() {
    update(items)
    items.clear()
  }
}

class BuilderExpandableGroup(group: Group) : ExpandableGroup(group) {
  fun item(
    @LayoutRes layoutRes: Int,
    data: Any? = null,
    block: View.(Int) -> Unit
  ) {
    add(
      BuilderItem(
        data?.hashCode()?.toLong() ?: ID_COUNTER.decrementAndGet(),
        layoutRes,
        data,
        block
      )
    )
  }

  fun expandable(
    @LayoutRes layoutRes: Int,
    block: View.(Int, ExpandableGroup) -> Unit,
    expandableBlock: BuilderExpandableGroup.() -> Unit
  ) {
    add(BuilderExpandableGroup(ExpandableBuilderItem(layoutRes, block)).apply(expandableBlock))
  }
}

data class BuilderItem(
  private val _id: Long,
  @LayoutRes private val layoutRes: Int,
  private val any: Any?,
  private val block: View.(Int) -> Unit
) : Item<GroupieViewHolder>(_id) {
  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(position)
  }
}

class ExpandableBuilderItem(
  @LayoutRes private val layoutRes: Int,
  private val block: View.(Int, ExpandableGroup) -> Unit
) : Item<GroupieViewHolder>(), ExpandableItem {

  private var onToggleListener: ExpandableGroup? = null

  override fun getLayout(): Int = layoutRes

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.root.block(position, onToggleListener!!)
  }

  override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
    this.onToggleListener = onToggleListener
  }
}
