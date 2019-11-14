package com.github.satoshun.example.builder

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

fun groupieAdapter(body: GroupAdapter<GroupieViewHolder>.() -> Unit): GroupAdapter<GroupieViewHolder> =
  GroupAdapter<GroupieViewHolder>().apply(body)
