package com.decadev.autocheck.ClickListener

import com.decadev.autocheck.Model.Cars.Result

interface CarClickListener {
    fun onClickItem(detailItem: Result)
}