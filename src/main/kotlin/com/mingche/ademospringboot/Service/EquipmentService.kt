package com.mingche.ademospringboot.Service

import com.mingche.ademospringboot.Model.Equipment
import com.mingche.ademospringboot.Repository.EquipmentRepository
import org.springframework.stereotype.Service

@Service
class EquipmentService(private val repo: EquipmentRepository) {
    fun getEquipmentById(id: Int): Equipment?{
        val equipment = repo.findById(id)
        if(equipment.isEmpty) return null
        return equipment.get()
    }
}