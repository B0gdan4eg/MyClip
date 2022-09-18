package com.shcherbakov_bogdan.myclip.data

import java.util.*

class Transactions
    constructor(date: String, income: Boolean, amount: Double) {
    private var date: Date = Date(2022,9,9)
    private var income: Boolean = false
    private var amount: Double = 0.00

    fun setDate() {

    }
    fun getDateDay(): String {
        return date.day.toString()
    }
}
