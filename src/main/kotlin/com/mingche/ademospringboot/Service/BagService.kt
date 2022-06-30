package com.mingche.ademospringboot.Service

import com.mingche.ademospringboot.Model.*
import com.mingche.ademospringboot.Repository.BagRepository
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