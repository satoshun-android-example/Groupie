package com.github.satoshun.example.builder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class BuilderActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.builder_act)

    val recycler = findViewById<RecyclerView>(R.id.recycler)
    recycler.layoutManager = LinearLayoutManager(this)

    recycler.adapter = groupieAdapter {
      +SampleItem()
    }
  }
}

class SampleItem : Item<GroupieViewHolder>() {
  override fun getLayout(): Int = R.layout.header_item

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
  }
}
