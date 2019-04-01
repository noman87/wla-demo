package com.purevpn.core.iView

interface BindableAdapter<T> {
    fun setData(items: List<T>)
    fun changedPositions(positions: Set<Int>)
}