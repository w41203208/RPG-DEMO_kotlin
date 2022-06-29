package com.mingche.ademospringboot.Service

import com.mingche.ademospringboot.Model.*
import com.mingche.ademospringboot.Repository.BagRepository
import org.springframework.stereotype.Service

@Service
class BagService(private val bagRepository: BagRepository) {
    fun addItemInUserBag(user: User, equipment: Equipment) {
        bagRepository.save(Bag(user, equipment))
    }
}