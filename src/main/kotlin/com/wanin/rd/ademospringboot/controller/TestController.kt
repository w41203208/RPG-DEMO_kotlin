package com.wanin.rd.ademospringboot.controller



import com.wanin.rd.ademospringboot.dto.test.AddTestDTO
import com.wanin.rd.ademospringboot.dto.test.TestDTO
import com.wanin.rd.ademospringboot.model.Test
import com.wanin.rd.ademospringboot.service.TestService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = [TestController.BASE_TEST_URL])
class TestController(private val testService: TestService) {

    @GetMapping("/allTests")
    fun getAllTests(): MutableList<Test> {
        val testList: MutableList<Test> = testService.getAllTests()
        testList.forEach{
            println("\n\n")
            println(it.name)
            println("\n\n")
        }
        return testList
    }

    @PostMapping("/addTest")
    fun addTest(@RequestBody addTest: AddTestDTO): Test {
        return testService.addTest(addTest)
    }

    companion object {
        const val BASE_TEST_URL: String = "/test"
    }
}