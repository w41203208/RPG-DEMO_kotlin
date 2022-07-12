package com.wanin.rd.ademospringboot.service

import com.wanin.rd.ademospringboot.dto.auth.RegistrantDTO
import com.wanin.rd.ademospringboot.dto.user.UpdatingUserDataDTO
import com.wanin.rd.ademospringboot.model.User
import com.wanin.rd.ademospringboot.repository.UserRepository
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
    fun registerUser(registrant: RegistrantDTO): User?{
        // if username is existed, return null
        if(checkUserNameIsExist(registrant.name)) return null
        return userRepository.save( User(registrant.name, registrant.password))
    }
    /**
     * login user with username and user password
     */
    fun loginUser(name: String, password: String): User?{
        return userRepository.findByNameAndPassword(name, password)
    }
    /**
    * get user data by id
    * */
    fun getUserById(id: String): User? {
        val user = userRepository.findById(id)
        if(user.isEmpty) return null
        return user.get()
    }
    fun updateUserData(updateUserData: UpdatingUserDataDTO): User?{
        val oldUser = userRepository.findById(updateUserData.id)
        return if(oldUser.isEmpty){
            null
        }else{
            var newUser = oldUser.get()
            newUser.name = updateUserData.name
            newUser.password = updateUserData.password
            newUser.updateAt = LocalDate.now()
            userRepository.save(newUser)
            newUser
        }
    }
    fun saveUserData(user: User): User{
        return userRepository.save(user)
    }
    /**
    * 判斷是否已經有相同的名稱
    * */
    fun checkUserNameIsExist(name: String): Boolean{
        return userRepository.findByName(name) != null
    }
//    fun check(checkfunction: (args: Any) -> Any): () -> Any {
//        return checkfunction
//    }
}