package com.wanin.rd.ademospringboot.repository;

import com.wanin.rd.ademospringboot.model.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : JpaRepository<Test, String> {
    override fun findAll(): MutableList<Test>

//    fun addByName(@Param("name") name: String): Test
}