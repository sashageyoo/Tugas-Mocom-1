package com.example.barangapp.data

data class Barang (
    val id: String,
    val name: String,
    val stock: Int
)

object Barangs {
    private val barangs = mutableListOf<Barang>()

    fun addBarang(barang: Barang) {
        barangs.add(barang)
    }

    fun getBarang(): List<Barang> {
        return barangs.toList()
    }
}