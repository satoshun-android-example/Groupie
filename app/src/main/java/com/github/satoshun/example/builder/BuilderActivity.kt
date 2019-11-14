package com.github.satoshun.example.builder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.satoshun.example.R
import com.github.satoshun.example.databinding.BuilderActBinding
import com.github.satoshun.example.databinding.SampleItemBinding

class BuilderActivity : AppCompatActivity() {
  private lateinit var binding: BuilderActBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = BuilderActBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recycler.layoutManager = LinearLayoutManager(this)
    binding.recycler.adapter = groupieAdapter {
      item(R.layout.sample_item) {
        val binding = SampleItemBinding.bind(this)
        binding.title.text = "HOGE"
      }
      expandable(
        R.layout.sample_item,
        block = { _, toggle ->
          val binding = SampleItemBinding.bind(this)
          binding.title.text = "EXPANDABLE"
          binding.root.setOnClickListener {
            toggle.onToggleExpanded()
          }
        }) {
        item(R.layout.sample_item) {
          val binding = SampleItemBinding.bind(this)
          binding.title.text = "CHILD1"
        }
        item(R.layout.sample_item) {
          val binding = SampleItemBinding.bind(this)
          binding.title.text = "CHILD2"
        }
      }
    }
  }
}
