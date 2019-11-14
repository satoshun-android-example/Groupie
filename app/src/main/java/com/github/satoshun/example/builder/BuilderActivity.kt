package com.github.satoshun.example.builder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.R

class BuilderActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.builder_act)

    val recycler = findViewById<RecyclerView>(R.id.recycler)
    recycler.layoutManager = LinearLayoutManager(this)

    recycler.adapter = groupieAdapter {
    }
  }
}
