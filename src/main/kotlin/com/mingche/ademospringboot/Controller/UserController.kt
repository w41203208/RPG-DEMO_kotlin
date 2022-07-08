package com.mingche.ademospringboot.Controller


import com.mingche.ademospringboot.Dto.UserDTO.*
import com.mingche.ademospringboot.Model.Bag
import com.mingche.ademospringboot.Model.Equipment
import com.mingche.ademospringboot.Model.User
import com.mingche.ademospringboot.Service.BagService
import com.mingche.ademospringboot.Service.EquipmentService
import com.mingche.ademospringboot.Service.UserService
import com.mingche.ademospringboot.Util.Response
import org.springframework.web.bind.annotation.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties


@RestController
@RequestMapping(value = [UserController.BASE_USER_URL])
class UserController(
    private val userService: UserService,
    private val equipmentService: EquipmentService,
    private val bagService: BagService
) {
    @GetMapping("/getAllUsers")
    fun getAllUsers(): Response<MutableList<User>> {
        return Response(
            state = "Success",
            payload = userService.getAllUsers()
        )
    }
    @PostMapping("/registerUser")
    fun registerUser(@RequestBody registrant: RegistrantDTO): Response<User?> {
        println("test")
        val user = userService.registerUser(registrant)
        return Response(
            state = if (isNull(user)) "BadRequest" else "Success",
            payload = user,
            message = if (isNull(user)) "User name is exist" else "",
        )
    }

    @PostMapping("/loginUser")
    fun loginUser(@RequestBody loginUser: LoginUserDTO): Response<LoginUserOutputDTO?>{
        val user = userService.loginUser(loginUser.name, loginUser.password)
        return Response(
            state = if (isNull(user)) "NotFound" else "Success",
            payload = if (isNull(user)) null else LoginUserOutputDTO(
                id = user!!.id,
                name = user.name,
                bodySlot = user.body_slot,
                handSlot = user.hand_slot,
                userBag = user.user_Bag,
            ),
            message = if (isNull(user)) "User name is not exist" else "",
        )
    }

    @GetMapping("/getUserById")
    fun getUserById(@RequestParam id: String): Response<UserDataDTO?> {
        val user = userService.getUserById(id)
        return Response(
            state = if (isNull(user)) "NotFound" else "Success",
            payload = if (isNull(user)) null else UserDataDTO(
                user!!.id,
                user.name
            )
        )
    }
    @GetMapping("/getUserEquipmentsById")
    fun getUserEquipmentsById(@RequestParam id: String): Response<MutableList<Bag>?>{
        val user = userService.getUserById(id)
        return Response(
            state = if (isNull(user)) "NotFound" else "Success",
            payload = user?.user_Bag
        )
    }
    @PostMapping("/addItemInUserBag")
    fun addItemInUserBag(@RequestBody addItemInBag: AddingItemInBagDTO): Response<MutableList<Bag>?> {
        val equipment = equipmentService.getEquipmentById(addItemInBag.eid)
            ?: return Response(
                state = "NotFound",
                payload = null,
                message = "Equipment is not exist"
            )

        val user = userService.getUserById(addItemInBag.uid)
            ?: return Response(
                state = "NotFound",
                payload = null,
                message = "User is not exist"
            )
        bagService.addItemInUserBag(user, equipment)

        return getUserEquipmentsById(addItemInBag.uid)
    }
    @PostMapping("/deleteItemInUserBag/{bid}")
    fun deleteItemInUserBag(@PathVariable bid: String): Response<String>{
        bagService.deleteItemInUserBagById(bid)
        return Response(
            state = "Success",
            payload = bid,
        )
    }
    @PostMapping("/updateUserData")
    fun updateUserData(@RequestBody updateUserData: UpdatingUserDataDTO): Response<UserDataDTO?>{
        val user = userService.updateUserData(updateUserData)
        return Response(
            state = if(!isNull(user)) "Success" else "NotFound",
            payload = if (isNull(user)) null else UserDataDTO(
                user!!.id,
                user.name
            )
        )
    }
    @PostMapping("/mountedEquipment")
    fun mountedEquipment(@RequestBody mountedEquipmentInputDTO: MountedEquipmentInputDTO): Response<MountedEquipmentOutputDTO?>{
        val user = userService.getUserById(mountedEquipmentInputDTO.uid)
            ?: return Response(
                state = "NotFound",
                payload = null,
                message = "User is not exist"
            )
        val equipmentInBag = bagService.getItemInUserBagById(mountedEquipmentInputDTO.bid)
            ?: return Response(
                state = "NotFound",
                payload = null,
                message = "You don't have this equipment"
            )
        var tempEquipment: Equipment? = null
        when (equipmentInBag.equipment.type) {
            "Armor" -> {
                tempEquipment = user.body_slot
                user.body_slot = equipmentInBag.equipment
            }
            "Weapon" -> {
                tempEquipment = user.hand_slot
                user.hand_slot = equipmentInBag.equipment
            }
        }
        userService.saveUserData(user)
        if(!isNull(tempEquipment)) bagService.addItemInUserBag(user, tempEquipment!!)
        bagService.deleteItemInUserBagById(mountedEquipmentInputDTO.bid)

        val bag: MutableList<Bag> = userService.getUserById(mountedEquipmentInputDTO.uid)!!.user_Bag
        return Response(
            state = "Success",
            payload = MountedEquipmentOutputDTO(
                equipmentInBag.equipment,
                bag
            )
        )
    }

    @PostMapping("/unMountedEquipment")
    fun unMountedEquipment(@RequestBody unMountedEquipmentInputDTO: UnMountedEquipmentInputDTO): Response<UnMountedEquipmentOutputDTO?>{ //
        val user = userService.getUserById(unMountedEquipmentInputDTO.uid)
            ?: return Response(
                state = "NotFound",
                payload = null,
                message = "User is not exist"
            )

        val userSlot = getInstanceProperty(user, unMountedEquipmentInputDTO.slotType)
            ?: return Response(
                state = "NotFound",
                payload = null,
                message = "User doesn't has this slot"
            )
        val bag = bagService.addItemInUserBag(user, userSlot.getter.call(user) as Equipment)
        userSlot?.setter?.call(user, null)
        userService.saveUserData(user)

        return Response(
            state = "Success",
            payload = UnMountedEquipmentOutputDTO(
                slotType = userSlot.name,
                addBagItem = BagItem(
                    bid = bag.id,
                    item = bag.equipment
                )
            )
        )
    }

//    fun changeEquipment(unmountedEquipment: Equipment, mountedEquipment: Equipment): Unit{
//        val tempEquipment = unmountedEquipment
//        unmountedEquipment = equipmentInBag.equipment
//        if(!isNull(tempEquipment)) bagService.addItemInUserBag(user, equipmentInBag.equipment)
//        bagService.deleteItemInUserBagById(mountedEquipmentDTO.bid)
//    }

    fun isNull(a: Any?): Boolean{
        return a == null
    }

    @Suppress("UNCHECKED_CAST")
    fun getInstanceProperty(instance: Any, propertyName: String): KMutableProperty<Any>? {
        return instance::class.declaredMemberProperties.firstOrNull { it.name == propertyName } as KMutableProperty<Any>? //as KProperty1<Any, *>?
            ?: return null
    }

    @Suppress("UNCHECKED_CAST")
    fun <R> readInstanceProperty(instance: Any, propertyName: String): R?{
        val property = instance::class.declaredMemberProperties.firstOrNull{it.name == propertyName} as KMutableProperty<Any>? //as KProperty1<Any, *>?
            ?: return null

        return property.getter.call(instance) as R
    }

    @Suppress("UNCHECKED_CAST")
    fun <W> writeInstanceProperty(instance: Any, propertyName: String): W?{
        val property = instance::class.declaredMemberProperties.firstOrNull{it.name == propertyName} as KMutableProperty<Any>? //as KProperty1<Any, *>?
            ?: return null

        property.setter.call(instance, null)
        return instance as W
    }

//    @Suppress("UNCHECKED_CAST")
//    fun <R> writeInstanceProperty(instance: Any, propertyName: String): R?{
//        val property = instance::class.declaredMemberProperties.firstOrNull{it.name == propertyName} as KProperty1<Any, *>?
//            ?: return null
//        return property.get(instance) as R
//    }
//    fun <T> userIsExistOrNull(uid: String): Response<T?>{
//        val user = userService.getUserById(uid)
//            ?: return Response(
//                state = "NotFound",
//                payload = null,
//                message = "User is not exist"
//            )
//    }
    companion object {
        const val BASE_USER_URL: String = "api/v1/user"
    }
}



