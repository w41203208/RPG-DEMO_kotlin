package com.wanin.rd.ademospringboot.controller

import com.wanin.rd.ademospringboot.controller.UserController
import com.wanin.rd.ademospringboot.service.BagService
import com.wanin.rd.ademospringboot.service.EquipmentService
import com.wanin.rd.ademospringboot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test

@WebMvcTest(controllers = [UserController::class])
class TestUserController {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var equipmentService: EquipmentService

    @MockBean
    private lateinit var bagService: BagService

    @Test
    fun getAllUserTest(){
        val request: RequestBuilder = MockMvcRequestBuilders.get("/user/all")
        val result: MvcResult = mvc.perform(request).andReturn()
        print(result.response.)
//            .andExpect(status.isOk()).andExpect(content().string(containsString("Hello, Mock"))).andReturn();
    }
}