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
        val bag = bagRepository.findById(bid)
        if(bag.isEmpty) return null
        return bag.get()
    }
    fun deleteItemInUserBagById(id: String){
        try{
            bagRepository.deleteById(id)
        }catch(e: Exception) {
            print(e)
        }
    }
}