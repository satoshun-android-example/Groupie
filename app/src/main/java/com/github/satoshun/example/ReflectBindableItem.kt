package com.github.satoshun.example

import android.view.View
import androidx.viewbinding.ViewBinding
import com.xwray.groupie.viewbinding.BindableItem

abstract class ReflectBindableItem<T : ViewBinding> : BindableItem<T> {
  constructor() : super()
  constructor(id: Long) : super(id)

  override fun initializeViewBinding(view: View): T {
    TODO("Not yet implemented")
  }
}
