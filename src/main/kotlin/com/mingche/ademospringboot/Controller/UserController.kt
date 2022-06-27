package com.mingche.ademospringboot.Controller

import com.mingche.ademospringboot.Controller.UserController.Companion.BASE_PERSON_URL
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = [BASE_PERSON_URL])
class UserController {

    @GetMapping()
    fun findAllUser(): String{
        return "test"
    }

    companion object {
        const val BASE_PERSON_URL: String = "api/v1/user"
    }
}