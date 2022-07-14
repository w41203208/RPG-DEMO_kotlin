package com.wanin.rd.ademospringboot.controller




import com.wanin.rd.ademospringboot.dto.bag.*
import com.wanin.rd.ademospringboot.dto.user.*
import com.wanin.rd.ademospringboot.model.Bag
import com.wanin.rd.ademospringboot.service.BagService
import com.wanin.rd.ademospringboot.service.EquipmentService
import com.wanin.rd.ademospringboot.service.UserService
import com.wanin.rd.ademospringboot.util.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties


@RestController
@RequestMapping(value = [UserController.BASE_USER_URL])
class UserController(
    private val userService: UserService,
    private val equipmentService: EquipmentService,
    private val bagService: BagService,
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
) {
    @GetMapping("/all")
    fun getAllUsers(): Response<Any> {
        logger.info("test")
        return Response.ok(userService.getAllUsers())
    }

    @GetMapping("")
    fun getUserById(@RequestParam id: String): Response<Any> {
        val user = userService.getUserById(id)
        return if(user == null){
            Response.notFound().body("Not found user by Id!")
        }else{
            Response.ok(UserDataDTO(
                id = user.id,
                name = user.name
            ))
        }
    }
    @GetMapping("/bagOfUser")
    fun getUserEquipmentsById(@RequestParam id: String): Response<Any>{
        val user = userService.getUserById(id)

        return if(user == null){
            Response.notFound().body("Not found user by Id!")
        }else{
            Response.ok(user.user_Bag)
        }
    }
    @PostMapping("/addItemInBag")
    fun addItemInUserBag(@RequestBody addItemInBag: AddingItemInBagDTO): Response<Any> {
        val equipment = equipmentService.getEquipmentById(addItemInBag.eid)
            ?: return Response.notFound().body("Equipment is not exist!")

        val user = userService.getUserById(addItemInBag.uid)
            ?: return Response.notFound().body("User is not exist!")

        bagService.addItemInUserBag(user, equipment)

        return getUserEquipmentsById(addItemInBag.uid)
    }
    @DeleteMapping("/itemInBag/{id}")
    fun deleteItemInUserBag(@PathVariable id: String): Response<String>{
        bagService.deleteItemInUserBagById(id)
        return Response.ok(id)
    }
    @PutMapping("/update")
    fun updateUserData(@RequestBody updateUserData: UpdatingUserDataDTO): Response<Any>{
        val user = userService.updateUserData(updateUserData)
        return if(user == null){
            Response.notFound().body("User is not exist!")
        }else{
            Response.ok(UserDataDTO(
                user.id,
                user.name
            ))
        }
    }
    @PostMapping("/mountedEquipment")
    fun mountedEquipment(@RequestBody mountedEquipmentInputDTO: MountedEquipmentInputDTO): Response<Any>{
        val user = userService.getUserById(mountedEquipmentInputDTO.uid)
            ?: return Response.notFound().body("User is not exist!")

        val equipment = bagService.getItemInUserBagById(mountedEquipmentInputDTO.bid)
            ?: return Response.notFound().body("You don't have this equipment")

        userService.mountedEquipment(user, equipment.equipment, mountedEquipmentInputDTO.bid)

        val bag: MutableList<Bag> = userService.getUserById(mountedEquipmentInputDTO.uid)!!.user_Bag
        return Response.ok(
            MountedEquipmentOutputDTO(
                equipment.equipment,
                bag
            )
        )
    }



    @PostMapping("/unMountedEquipment")
    fun unMountedEquipment(@RequestBody unMountedEquipmentInputDTO: UnMountedEquipmentInputDTO): Response<Any>{
        val user = userService.getUserById(unMountedEquipmentInputDTO.uid)
            ?: return Response.notFound().body("User is not exist!")

        val bag = userService.unmountedEquipment(unMountedEquipmentInputDTO.slotType, user)
            ?: return Response.notFound().body("User doesn't has this slot")


        return Response.ok(
            UnMountedEquipmentOutputDTO(
                slotType = unMountedEquipmentInputDTO.slotType,
                addBagItem = BagItem(
                    bid = bag.id,
                    item = bag.equipment
                )
            )
        )
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
    companion object {
        const val BASE_USER_URL: String = "/user"
    }
}



