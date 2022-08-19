package com.aapolis.asynccoroutinedemo

data class BankBalance(
    val balance: Int,
    val error: Boolean,
    val message: String
)