package com.example.budgetplanner

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            val query = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY," +
                    EXPENSE_COL + " TEXT, " +
                    AMOUNT_COL + " DOUBLE, " +
                    CATEGORY_COL + " TEXT)")
            db.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }

        fun addExpense(expense: Expense) {
            val db = readableDatabase
            val values = ContentValues()
            values.put(EXPENSE_COL, expense.expense)
            values.put(AMOUNT_COL, expense.amount)
            values.put(CATEGORY_COL, expense.category)
            db.insert(TABLE_NAME, null, values)
            db.close()
        }

        fun editExpense(id: Int, expense: Expense) {
            val db = readableDatabase
            val values = ContentValues()
            values.put(EXPENSE_COL, expense.expense)
            values.put(AMOUNT_COL, expense.amount)
            values.put(CATEGORY_COL, expense.category)
            db.update(TABLE_NAME, values, "$ID_COL = $id", null)
        }

        fun deleteExpenseById(id: Int): Int {
            val db = readableDatabase
            return db.delete(TABLE_NAME, "$ID_COL = $id", null)
        }

        fun removeAllExpenses() {
            val db = readableDatabase
            db.delete(TABLE_NAME, null, null)
        }

        companion object {
            private val DATABASE_NAME = "db"
            private val DATABASE_VERSION = 1
            val TABLE_NAME = "planner_table"
            val ID_COL = "id"
            val EXPENSE_COL = "expense"
            val AMOUNT_COL = "amount"
            val CATEGORY_COL = "category"
        }
}
