package com.wanin.rd.ademospringboot.controller


import com.wanin.rd.ademospringboot.controller.TestController
import com.wanin.rd.ademospringboot.service.TestService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@WebMvcTest(controllers = [TestController::class])
class TestTestController{

	@Autowired
	private lateinit var mvc: MockMvc

	@MockBean
	private lateinit var testService: TestService;

	@Test
	fun contextLoads(){

		println("test")
	}

	@Test
	fun Test(){
		val request: RequestBuilder = MockMvcRequestBuilders.get("/test/hi")
		val result: MvcResult = mvc.perform(request).andReturn()

	}


}
