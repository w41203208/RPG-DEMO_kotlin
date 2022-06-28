package com.mingche.ademospringboot.Service


import com.mingche.ademospringboot.Dto.TestDTO
import com.mingche.ademospringboot.Model.Test
import com.mingche.ademospringboot.Repository.TestRepository
import org.springframework.stereotype.Service


@Service
class TestService(private val testRepository: TestRepository) {
    fun getAllTests(): MutableList<Test> = testRepository.findAll()

    /*
    *
    * */
//    fun addTest(addTest: TestDTO.AddTestDTO): Test = testRepository.addByName(addTest.name)
    fun addTest(addTest: TestDTO.AddTestDTO): Test = testRepository.save(Test(addTest.name))
}