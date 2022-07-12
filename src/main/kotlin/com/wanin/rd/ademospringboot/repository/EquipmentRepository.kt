package com.wanin.rd.ademospringboot.repository;

import com.wanin.rd.ademospringboot.model.Equipment
import org.springframework.data.jpa.repository.JpaRepository

interface EquipmentRepository : JpaRepository<Equipment, Int> {
}