package com.mingche.ademospringboot.Service

import com.mingche.ademospringboot.Model.User
import com.mingche.ademospringboot.Repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): MutableList<User> = userRepository.findAll()
    fun registerUser(registrant: User): User = userRepository.save(registrant)
}