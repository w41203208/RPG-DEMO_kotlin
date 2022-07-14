package com.wanin.rd.ademospringboot.service

import com.wanin.rd.ademospringboot.dto.auth.RegistrantDTO
import com.wanin.rd.ademospringboot.dto.user.UpdatingUserDataDTO
import com.wanin.rd.ademospringboot.model.Bag
import com.wanin.rd.ademospringboot.model.Equipment
import com.wanin.rd.ademospringboot.model.User
import com.wanin.rd.ademospringboot.repository.BagRepository
import com.wanin.rd.ademospringboot.repository.UserRepository
import com.wanin.rd.ademospringboot.util.Util
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val bagRepository: BagRepository,
) {
    enum class Slot(val type: String){

        BodySlot("body_slot"),
        HandSlot("hand_slot");
    }
    /**
     * get all user data
     * */
    fun getAllUsers(): MutableList<User> {
        return userRepository.findAll()
    }
    /**
     * get user data by id
     * */
    fun getUserById(id: String): User?{
        return userRepository.findByIdOrNull(id)
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
    @Transactional
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
    @Transactional
    fun mountedEquipment(user: User, equipment: Equipment, id: String){
        var tempEquipment: Equipment? = null
        when (equipment.type) {
            "Armor" -> {
                tempEquipment = user.body_slot
                user.body_slot = equipment
            }
            "Weapon" -> {
                tempEquipment = user.hand_slot
                user.hand_slot = equipment
            }
        }
        userRepository.save(user)
        if(tempEquipment != null) bagRepository.save(Bag(user, equipment))
        bagRepository.deleteById(id)
    }
    @Transactional
    fun unmountedEquipment(slotType: String, user: User): Bag?{
        val equipment = getUserSlotEquipment(slotType, user) ?: return null

        val bag = bagRepository.save(Bag(user, equipment.get()))

        val user = setUserSlotEquipmentIsNull(slotType, user)

        userRepository.save(user)

        return bag
    }
//    @Deprecated("BYE")
//    fun saveUserData(user: User): User{
//        return userRepository.save(user)
//    }
    /**
    * 判斷是否已經有相同的名稱
    * */
    fun checkUserNameIsExist(name: String): Boolean{
        return userRepository.findByName(name) != null
    }
    fun getUserSlotEquipment(type: String, user: User): Optional<Equipment>?{
        when(type){
            Slot.BodySlot.type -> {
                return Optional.ofNullable(user.body_slot)
            }
            Slot.HandSlot.type -> {
                return Optional.ofNullable(user.hand_slot)
            }
        }
        return null
    }
    fun setUserSlotEquipmentIsNull(type: String, user: User): User{
        when(type){

            Slot.BodySlot.type -> {
                user.body_slot = null
            }
            Slot.HandSlot.type -> {
                user.hand_slot = null
            }
        }
        return user
    }


//    fun check(checkfunction: (args: Any) -> Any): () -> Any {
//        return checkfunction
//    }
}