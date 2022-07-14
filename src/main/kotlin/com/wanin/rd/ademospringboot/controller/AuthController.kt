package com.wanin.rd.ademospringboot.controller

import com.wanin.rd.ademospringboot.dto.auth.LoginUserInputDTO
import com.wanin.rd.ademospringboot.dto.auth.LoginUserOutputDTO
import com.wanin.rd.ademospringboot.dto.auth.RegistrantDTO
import com.wanin.rd.ademospringboot.service.UserService
import com.wanin.rd.ademospringboot.util.Response
import org.springframework.http.ResponseEntity
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
    fun registerUser(@RequestBody registrant: RegistrantDTO): Response<Any> {
        val user = userService.registerUser(registrant)
        return if(user == null){
            Response.badRequest().body("User name is exist!")
        }else{
            Response.ok(user)
        }
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody loginUser: LoginUserInputDTO): Response<Any>{
        val user = userService.loginUser(loginUser.name, loginUser.password)

        return if(user == null){
            Response.notFound().body("Your username or password is wrong!")
        }else{
            Response.ok(LoginUserOutputDTO(
                id = user.id,
                name = user.name,
                bodySlot = user.body_slot,
                handSlot = user.hand_slot,
                userBag = user.user_Bag,
            ))
        }
    }

    companion object {
        const val BASE_USER_URL: String = "/auth"
    }
}