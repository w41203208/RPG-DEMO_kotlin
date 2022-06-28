package com.mingche.ademospringboot.Controller


import com.mingche.ademospringboot.Model.User
import com.mingche.ademospringboot.Service.UserService
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = [UserController.BASE_USER_URL])
class UserController(private val userService:UserService) {

    @GetMapping("/allUsers")
    fun getAllUsers(): MutableList<User>{
        return userService.getAllUsers()
    }

    @PostMapping("/registerUser")
    fun addUser(@RequestBody registrant: User): User {
        return userService.registerUser(registrant)
    }

    companion object {
        const val BASE_USER_URL: String = "api/v1/user"
    }
}