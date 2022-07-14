package com.wanin.rd.ademospringboot.controller

import com.wanin.rd.ademospringboot.model.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestAuthController {
//    private val url: String = "/auth"

//    @Autowired
//    private lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    private lateinit var mvc: MockMvc

//    @Test
//    fun postLoginTest_withPasswordIsNotTure(){
//        val params: MutableMap<String, Any> = HashMap()
//        params["name"] = "jay"
//        params["password"] = "testttt"
//        val response = testRestTemplate.postForEntity<Any>("$url/login", params)
//        println(response.body.toString())

    @Test
    fun postRegisterTest_withPasswordAndNameIsExist(){
        var request: RequestBuilder = MockMvcRequestBuilders.post("/auth/register")
            .content("{\"name\": \"Jay\",\"password\": \"test\"}")
            .contentType(MediaType.APPLICATION_JSON)
        val result: MvcResult = mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
    }
    @Test
    fun postRegisterTest_withPasswordAndNameIsTrue(){
        var request: RequestBuilder = MockMvcRequestBuilders.post("/auth/register")
            .content("{\"name\": \"Jayy\",\"password\": \"test\"}")
            .contentType(MediaType.APPLICATION_JSON)
        val result: MvcResult = mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
    }

    @Test
    fun postLoginTest_withPasswordAndNameIsTrue(){
        var request: RequestBuilder = MockMvcRequestBuilders.post("/auth/login")
            .content("{\"name\": \"Jayy\",\"password\": \"test\"}")
            .contentType(MediaType.APPLICATION_JSON)
        val result: MvcResult = mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
    }
    @Test
    fun postLoginTest_withPasswordIsFalseAndNameIsTrue(){
        var request: RequestBuilder = MockMvcRequestBuilders.post("/auth/login")
            .content("{\"name\": \"Jayy\",\"password\": \"testt\"}")
            .contentType(MediaType.APPLICATION_JSON)
        val result: MvcResult = mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string("{\"status\":404,\"message\":\"Your username or password is wrong!\"}"))
            .andReturn()
    }

    @Test
    fun postLoginTest_withPasswordIsTrueAndNameIsFalse(){
        var request: RequestBuilder = MockMvcRequestBuilders.post("/auth/login")
            .content("{\"name\": \"Jayyy\",\"password\": \"test\"}")
            .contentType(MediaType.APPLICATION_JSON)
        val result: MvcResult = mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string("{\"status\":404,\"message\":\"Your username or password is wrong!\"}"))
            .andReturn()
    }


}