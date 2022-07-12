package com.wanin.rd.ademospringboot.service

import com.wanin.rd.ademospringboot.model.Equipment
import com.wanin.rd.ademospringboot.repository.EquipmentRepository
import org.springframework.stereotype.Service

@Service
class EquipmentService(private val equipmentRepo: EquipmentRepository) {
    fun getEquipmentById(id: Int): Equipment?{
        val equipment = equipmentRepo.findById(id)
        if(equipment.isEmpty) return null
        return equipment.get()
    }

    fun getAllEquipment(): MutableList<Equipment>{
        return equipmentRepo.findAll()
    }
}