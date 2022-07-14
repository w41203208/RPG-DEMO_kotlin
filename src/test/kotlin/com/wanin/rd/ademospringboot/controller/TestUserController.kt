package com.wanin.rd.ademospringboot.controller


import com.wanin.rd.ademospringboot.dto.user.UpdatingUserDataDTO
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity


// 使用 Mock 為 Server 不啟動

//@WebMvcTest(controllers = [UserController::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestUserController {
    private val url: String = "/user"

//    @Autowired
//    private lateinit var mvc: MockMvc

//    @MockBean
//    private lateinit var userService: UserService
//
//    @MockBean
//    private lateinit var equipmentService: EquipmentService
//
//    @MockBean
//    private lateinit var bagService: BagService

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun getAllUserTest(){
        val response = testRestTemplate.getForEntity<Any>("$url/all")
        println(response.body.toString())
    }

    @Test
    fun getUserByIdTest(){
        val params: MutableMap<String, Any> = HashMap()
        params["id"] = "402881e281f194310181f194468b0000"
        val response = testRestTemplate.getForEntity<Any>("$url/?id={id}", params)
        println(response.body.toString())
    }

    @Test
    fun putUpdateUserDataTest(){
        val userUpdate: UpdatingUserDataDTO = UpdatingUserDataDTO(  // 這裡用 MutableMap or DTO 都可以
            id = "402881e281f66c460181f66c76fa0000",
            name = "jay",
            password = "testttt"
        )
        val response = testRestTemplate.put("$url/update", userUpdate)
        println(response.toString())

    }

    @Test
    fun deleteItemInUserBagTest(){
        val response = testRestTemplate.delete("$url/itemInBag/402881e281f1b37c0181f1b7e0c10000")
        println(response)
    }


//    @Test
//    fun getAllUserTest(){
//        val request: RequestBuilder = MockMvcRequestBuilders.get("/user/all")
//        val result: MvcResult = mvc.perform(request).andExpect(status().isOk).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
//        print(result.response.contentAsString)
////            .andExpect(status.isOk()).andExpect(content().string(containsString("Hello, Mock"))).andReturn();
//    }
//
//    @Test
//    fun getUserById(){
//        var request: RequestBuilder = MockMvcRequestBuilders.get("/user/?id=402881e281f194310181f194468b0000")
//        val result: MvcResult = mvc.perform(request).andExpect(status().isOk).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn()
//        print(result.response.contentAsString)
//    }
}