package com.example.guia3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VehicleDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "vehicles.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "vehicles"
        private const val COLUMN_ID = "id"
        private const val COLUMN_BRAND = "brand"
        private const val COLUMN_MODEL = "model"
        private const val COLUMN_YEAR = "year"
        private const val COLUMN_MILEAGE = "mileage"
        private const val COLUMN_AVAILABLE = "available"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
                $COLUMN_BRAND TEXT, 
                $COLUMN_MODEL TEXT, 
                $COLUMN_YEAR INTEGER, 
                $COLUMN_MILEAGE INTEGER, 
                $COLUMN_AVAILABLE INTEGER, 
                $COLUMN_IMAGE TEXT
            )
        """
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertVehicle(brand: String, model: String, year: Int, mileage: Int, available: Boolean, imageUri: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_BRAND, brand)
            put(COLUMN_MODEL, model)
            put(COLUMN_YEAR, year)
            put(COLUMN_MILEAGE, mileage)
            put(COLUMN_AVAILABLE, if (available) 1 else 0)
            put(COLUMN_IMAGE, imageUri)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllVehiclesWithDetails(): List<List<String>> {
        val vehicles = mutableListOf<List<String>>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID)).toString()
                val brand = getString(getColumnIndexOrThrow(COLUMN_BRAND))
                val model = getString(getColumnIndexOrThrow(COLUMN_MODEL))
                val year = getInt(getColumnIndexOrThrow(COLUMN_YEAR)).toString()
                val mileage = getInt(getColumnIndexOrThrow(COLUMN_MILEAGE)).toString()
                val available = if (getInt(getColumnIndexOrThrow(COLUMN_AVAILABLE)) == 1) "Sí" else "No"

                vehicles.add(listOf(id, brand, model, year, mileage, available))
            }
        }
        cursor.close()
        db.close()
        return vehicles
    }
    fun getVehicleById(id: Int): List<String>? {
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_ID = ?", arrayOf(id.toString()), null, null, null)
        return if (cursor.moveToFirst()) {
            val brand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BRAND))
            val model = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL))
            val year = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_YEAR)).toString()
            val mileage = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MILEAGE)).toString()
            val available = if (cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AVAILABLE)) == 1) "Sí" else "No"
            cursor.close()
            db.close()
            listOf(brand, model, year, mileage, available)
        } else {
            cursor.close()
            db.close()
            null
        }
    }


    fun updateVehicle(id: Int, brand: String, model: String, year: Int, mileage: Int, available: Boolean, imageUri: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_BRAND, brand)
            put(COLUMN_MODEL, model)
            put(COLUMN_YEAR, year)
            put(COLUMN_MILEAGE, mileage)
            put(COLUMN_AVAILABLE, if (available) 1 else 0)
            put(COLUMN_IMAGE, imageUri)
        }
        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun deleteVehicle(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    data class Vehicle(
        val id: Int,
        val brand: String,
        val model: String,
        val year: Int,
        val mileage: Int,
        val available: Boolean,
        val imageUri: String
    )
}
