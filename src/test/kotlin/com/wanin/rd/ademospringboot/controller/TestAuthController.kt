package com.wanin.rd.ademospringboot.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestAuthController {
    private val url: String = "/auth"

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate


    @Test
    fun postLoginTest(){
        val params: MutableMap<String, Any> = HashMap()
        params["name"] = "jay"
        params["password"] = "testttt"
        val response = testRestTemplate.postForEntity<Any>("$url/login", params)
        println(response.body.toString())
    }

}