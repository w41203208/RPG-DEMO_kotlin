package com.mingche.ademospringboot.Controller


import com.mingche.ademospringboot.Dto.UserDTO.*
import com.mingche.ademospringboot.Model.Bag
import com.mingche.ademospringboot.Model.User
import com.mingche.ademospringboot.Service.BagService
import com.mingche.ademospringboot.Service.EquipmentService
import com.mingche.ademospringboot.Service.UserService
import com.mingche.ademospringboot.Util.Response
import org.springframework.web.bind.annotation.*


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
    fun registerUser(@RequestBody registrant: Registrant): Response<User?> {
        val user = userService.registerUser(registrant)
        return Response(
            state = if (user == null) "BadRequest" else "Success",
            payload = user,
            message = "User name is exist"
        )
    }

    @GetMapping("/getUserById")
    fun getUserById(@RequestParam id: String): Response<UserData?> {
        val user = userService.getUserById(id)
        return Response(
            state = if (user == null) "NotFound" else "Success",
            payload = if (user == null ) null else UserData(
                user.id,
                user.name
            )
        )
    }
    @GetMapping("/getUserEquipmentsById")
    fun getUserEquipmentsById(@RequestParam id: String): Response<MutableList<Bag>?>{
        val user = userService.getUserById(id)
        return Response(
            state = if (user == null) "NotFound" else "Success",
            payload = user?.user_Bag
        )
    }
    @PostMapping("/addItemInUserBag")
    fun addItemInUserBag(@RequestBody addItemInBag: AddingItemInBag): Response<MutableList<Bag>?> {
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
    @PostMapping("/updateUserData")
    fun updateUserData(@RequestBody updateUserData: UpdatingUserData): Response<UserData?>{
        val user = userService.updateUserData(updateUserData)
        return Response(
            state = if(user != null) "Success" else "NotFound",
            payload = if (user == null ) null else UserData(
                user.id,
                user.name
            )
        )
    }
    companion object {
        const val BASE_USER_URL: String = "api/v1/user"
    }
}

