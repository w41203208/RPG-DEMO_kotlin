package com.wanin.rd.ademospringboot.controller

import com.wanin.rd.ademospringboot.dto.auth.LoginUserInputDTO
import com.wanin.rd.ademospringboot.dto.auth.LoginUserOutputDTO
import com.wanin.rd.ademospringboot.dto.auth.RegistrantDTO
import com.wanin.rd.ademospringboot.model.User
import com.wanin.rd.ademospringboot.service.UserService
import com.wanin.rd.ademospringboot.util.Response
import com.wanin.rd.ademospringboot.util.Util
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = [AuthController.BASE_USER_URL])
class AuthController (
    private val userService: UserService,
){
    @PostMapping("/register")
    fun registerUser(@RequestBody registrant: RegistrantDTO): Response<User?> {
        val user = userService.registerUser(registrant)
        return Response(
            state = if (Util.isNull(user)) "BadRequest" else "Success",
            payload = user,
            message = if (Util.isNull(user)) "User name is exist" else "",
        )
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody loginUser: LoginUserInputDTO): Response<LoginUserOutputDTO?>{
        val user = userService.loginUser(loginUser.name, loginUser.password)
        return Response(
            state = if (Util.isNull(user)) "NotFound" else "Success",
            payload = if (Util.isNull(user)) null else LoginUserOutputDTO(
                id = user!!.id,
                name = user.name,
                bodySlot = user.body_slot,
                handSlot = user.hand_slot,
                userBag = user.user_Bag,
            ),
            message = if (Util.isNull(user)) "User name is not exist" else "",
        )
    }

    companion object {
        const val BASE_USER_URL: String = "/auth"
    }
}