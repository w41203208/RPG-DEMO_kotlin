package com.mingche.ademospringboot.Dto

import com.mingche.ademospringboot.Model.Bag
import com.mingche.ademospringboot.Model.Equipment

class UserDTO {
    class RegistrantDTO(
        val name: String,
        val password: String,
    )
    class LoginUserDTO(
        val name: String,
        val password: String,
    )
    class LoginUserOutputDTO(
        val id: String,
        val name: String,
        val bodySlot: Equipment?,
        val handSlot: Equipment?,
        val userBag: MutableList<Bag> ,
    )
    class AddingItemInBagDTO(
        val uid: String,
        val eid: Int,
    )
    class UserDataDTO(
        val id: String,
        val name: String,
    )
    class UpdatingUserDataDTO(
        val id: String,
        val name: String,
        val password: String,
    )
    class MountedEquipmentInputDTO(
        val uid: String,
        val bid: String,
    )
    class MountedEquipmentOutputDTO(
        val mountedEquipment: Equipment,
        val bag: MutableList<Bag>,
    )
    class UnMountedEquipmentInputDTO(
        val uid: String,
        val slotType: String,
    )
    class UnMountedEquipmentOutputDTO(
        val slotType: String,
        val addBagItem: BagItem
    )
    class BagItem(
        val bid: String,
        val item: Equipment,
    )
}