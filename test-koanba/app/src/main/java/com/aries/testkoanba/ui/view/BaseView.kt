package com.aries.testkoanba.ui.view

interface BaseView {
    fun showProgressBar()
    fun hideProgressBar()
    fun isConnected(): Boolean
}