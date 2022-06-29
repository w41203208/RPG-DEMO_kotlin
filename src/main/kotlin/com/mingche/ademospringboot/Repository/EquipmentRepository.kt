package com.mingche.ademospringboot.Repository;

import com.mingche.ademospringboot.Model.Equipment
import org.springframework.data.jpa.repository.JpaRepository

interface EquipmentRepository : JpaRepository<Equipment, Int> {
}