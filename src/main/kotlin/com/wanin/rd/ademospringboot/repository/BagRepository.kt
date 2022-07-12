package com.wanin.rd.ademospringboot.repository;

import com.wanin.rd.ademospringboot.model.Bag
import org.springframework.data.jpa.repository.JpaRepository

interface BagRepository : JpaRepository<Bag, String> {
}