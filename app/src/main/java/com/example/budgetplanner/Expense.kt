package com.example.budgetplanner

data class Expense(
    val id: Int?,
    val expense: String,
    val amount: Double,
    val category: String
)