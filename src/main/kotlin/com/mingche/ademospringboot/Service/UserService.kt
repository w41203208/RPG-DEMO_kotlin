package com.mingche.ademospringboot.Service

import com.mingche.ademospringboot.Dto.UserDTO.*
import com.mingche.ademospringboot.Model.User
import com.mingche.ademospringboot.Repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getAllUsers(): MutableList<User> {
        return userRepository.findAll()
    }

    /**
     * register user with username and user password, and if username is existed that not to register successfully.
     */
    fun registerUser(registrant: Registrant): User?{
        // if username is existed, return null
        if(checkUserNameIsExist(registrant.name)) return null

        return userRepository.save( User(registrant.name, registrant.password))
    }
    /**
    * get user data by id
    * */
    fun getUserById(id: String): User? {
        val user = userRepository.findById(id)
        if(user.isEmpty) return null
        return user.get()
    }
    fun updateUserData(updateUserData: UpdatingUserData): User?{
        val oldUser = userRepository.findById(updateUserData.id)
        return if(oldUser.isEmpty){
            null
        }else{
            var newUser = oldUser.get()
//            var newUser = oldUser.get().apply {
//                print(this.id)
//                print(this.name)
//                print(this.user_Bag)
//            }
            newUser.name = updateUserData.name
            newUser.password = updateUserData.password
            newUser.updateAt = LocalDate.now()
            userRepository.save(newUser)
            newUser
        }
    }
    /**
    * 判斷是否已經有相同的名稱
    * */
    fun checkUserNameIsExist(name: String): Boolean{
        return userRepository.findByName(name) != null
    }
}