package com.purevpn.core.iView

import androidx.lifecycle.MutableLiveData

interface BindableAdapter<T> {
    fun setData(items: MutableLiveData<List<T>>)
    fun changedPositions(positions: Set<Int>)
}