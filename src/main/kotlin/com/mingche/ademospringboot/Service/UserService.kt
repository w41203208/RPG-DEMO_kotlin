package com.mingche.ademospringboot.Service

import com.mingche.ademospringboot.Dto.UserDTO
import com.mingche.ademospringboot.Model.User
import com.mingche.ademospringboot.Repository.UserRepository
import com.mingche.ademospringboot.Util.Message
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): Message<MutableList<User>> {
        return Message<MutableList<User>>(
            "Success",
            userRepository.findAll()
        )
    }
    fun registerUser(registrant: UserDTO.Registrant): Message<User>{

        return Message(
            "Success",
            userRepository.save( User(registrant.name, registrant.password))
        )
    }

    fun checkUserNameIsExist(name: String): Boolean{
//        if(name != userRepository.getByName(name))
        return true
    }
}