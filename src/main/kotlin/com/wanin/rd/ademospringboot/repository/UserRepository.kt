package com.wanin.rd.ademospringboot.repository;

import com.wanin.rd.ademospringboot.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager

@Repository
interface UserRepository : JpaRepository<User, String> {

    override fun findAll(): MutableList<User>
    override fun <S : User?> save(entity: S): S

    fun findByNameAndPassword(name: String, password: String): User?
    fun findByName(name: String): User?
}

