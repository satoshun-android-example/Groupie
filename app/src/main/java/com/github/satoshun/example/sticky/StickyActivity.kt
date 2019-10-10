package com.github.satoshun.example.sticky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.StickyActBinding

class StickyActivity : AppCompatActivity() {

  private lateinit var binding: StickyActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.sticky_act)
    setSupportActionBar(binding.toolbar)

    with(binding.recycler) {
      layoutManager = LinearLayoutManager(this@StickyActivity)
      val mainAdapter = MainAdapter()
      adapter = mainAdapter
      addItemDecoration(StickyHeaderItemDecoration(mainAdapter))
    }
  }
}
