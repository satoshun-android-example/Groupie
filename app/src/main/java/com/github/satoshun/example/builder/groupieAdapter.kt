package com.github.satoshun.example.builder

import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

fun groupieAdapter(body: BuilderGroupAdapter.() -> Unit): BuilderGroupAdapter =
  BuilderGroupAdapter().apply(body)

class BuilderGroupAdapter : GroupAdapter<GroupieViewHolder>() {
  operator fun Group.unaryPlus() {
    add(this)
  }
}
