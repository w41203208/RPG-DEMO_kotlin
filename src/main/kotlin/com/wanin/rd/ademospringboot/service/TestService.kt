package com.wanin.rd.ademospringboot.service


import com.wanin.rd.ademospringboot.dto.test.AddTestDTO
import com.wanin.rd.ademospringboot.model.Test
import com.wanin.rd.ademospringboot.repository.TestRepository
import org.springframework.stereotype.Service


@Service
class TestService(private val testRepository: TestRepository) {
    fun getAllTests(): MutableList<Test> = testRepository.findAll()

    /*
    *
    * */
//    fun addTest(addTest: TestDTO.AddTestDTO): Test = testRepository.addByName(addTest.name)
    fun addTest(addTest: AddTestDTO): Test = testRepository.save(Test(addTest.name))
}