package com.wanin.rd.ademospringboot.service

import com.wanin.rd.ademospringboot.model.*
import com.wanin.rd.ademospringboot.repository.BagRepository
import org.springframework.stereotype.Service

@Service
class BagService(private val bagRepository: BagRepository) {
    fun addItemInUserBag(user: User, equipment: Equipment): Bag {
        return bagRepository.save(Bag(user, equipment))
    }
    fun getItemInUserBagById(bid: String): Bag?{
        val equipmentInBag = bagRepository.findById(bid)
        if(equipmentInBag.isEmpty) return null
        return equipmentInBag.get()
    }
    fun deleteItemInUserBagById(id: String){
        bagRepository.deleteById(id)
    }
}