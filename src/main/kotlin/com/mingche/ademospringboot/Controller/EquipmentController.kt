package com.mingche.ademospringboot.Controller

import com.mingche.ademospringboot.Model.Equipment
import com.mingche.ademospringboot.Service.EquipmentService
import com.mingche.ademospringboot.Util.Response

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
        return Response(
            state = "Success",
            payload = equipmentService.getAllEquipment(),
            message = "",
        )

    }

    companion object{
        const val BASE_EQUIPMENT_URL: String = "api/v1/equipment"
    }
}