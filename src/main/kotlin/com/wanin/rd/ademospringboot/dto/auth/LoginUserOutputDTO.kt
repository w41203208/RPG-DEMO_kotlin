package com.wanin.rd.ademospringboot.dto.auth

import com.wanin.rd.ademospringboot.model.Bag
import com.wanin.rd.ademospringboot.model.Equipment

class LoginUserOutputDTO (
    val userBag: MutableList<Bag>,
    val id: String,
    val name: String,
    val bodySlot: Equipment?,
    val handSlot: Equipment?,
)