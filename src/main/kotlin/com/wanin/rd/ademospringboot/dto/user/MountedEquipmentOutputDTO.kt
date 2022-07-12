package com.wanin.rd.ademospringboot.dto.user

import com.wanin.rd.ademospringboot.model.Bag
import com.wanin.rd.ademospringboot.model.Equipment

class MountedEquipmentOutputDTO(
    val mountedEquipment: Equipment,
    val bag: MutableList<Bag>,
)