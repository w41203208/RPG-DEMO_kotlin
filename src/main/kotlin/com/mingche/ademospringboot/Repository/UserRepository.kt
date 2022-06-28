package com.mingche.ademospringboot.Repository;

import com.mingche.ademospringboot.Model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
interface UserRepository : JpaRepository<User, String> {
    override fun getById(Id: String): User
    override fun findAll(): MutableList<User>
    override fun <S : User?> save(entity: S): S
}

