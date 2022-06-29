package com.mingche.ademospringboot.Repository;

import com.mingche.ademospringboot.Model.Bag
import org.springframework.data.jpa.repository.JpaRepository

interface BagRepository : JpaRepository<Bag, String> {
}