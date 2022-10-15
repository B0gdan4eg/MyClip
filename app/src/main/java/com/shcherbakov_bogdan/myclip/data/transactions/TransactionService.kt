package com.shcherbakov_bogdan.myclip.data.transactions

typealias TransactionListener = (transitions: List<Transactions>) -> Unit

class TransactionService {

    private var transactions = mutableListOf<Transactions>()
    private val listener = mutableListOf<TransactionListener>()

}