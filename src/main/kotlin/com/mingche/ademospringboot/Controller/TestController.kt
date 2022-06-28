package com.mingche.ademospringboot.Controller



import com.mingche.ademospringboot.Dto.TestDTO
import com.mingche.ademospringboot.Model.Test
import com.mingche.ademospringboot.Service.TestService
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
    fun addTest(@RequestBody addTest: TestDTO.AddTestDTO): Test {
        return testService.addTest(addTest)
    }

    companion object {
        const val BASE_TEST_URL: String = "api/v1/test"
    }
}