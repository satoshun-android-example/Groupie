package com.github.satoshun.example

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.satoshun.example.builder.BuilderActivity
import com.github.satoshun.example.sticky.StickyActivity

class AppActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.app_act)

    findViewById<View>(R.id.sticky).setOnClickListener {
      startActivity(
        Intent(this@AppActivity, StickyActivity::class.java)
      )
    }

    findViewById<View>(R.id.builder).setOnClickListener {
      startActivity(
        Intent(this@AppActivity, BuilderActivity::class.java)
      )
    }
  }
}
