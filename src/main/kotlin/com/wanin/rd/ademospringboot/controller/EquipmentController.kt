package com.wanin.rd.ademospringboot.controller

import com.wanin.rd.ademospringboot.model.Equipment
import com.wanin.rd.ademospringboot.service.EquipmentService
import com.wanin.rd.ademospringboot.util.Response

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = [EquipmentController.BASE_EQUIPMENT_URL])
class EquipmentController(
    private val equipmentService: EquipmentService
) {

    @GetMapping("/allEquipment")
    fun getAllEquipments(): Response<MutableList<Equipment>> {
        return Response.ok(
            equipmentService.getAllEquipment(),
        )

    }

    companion object{
        const val BASE_EQUIPMENT_URL: String = "/equipment"
    }
}