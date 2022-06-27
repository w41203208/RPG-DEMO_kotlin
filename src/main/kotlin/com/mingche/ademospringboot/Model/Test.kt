package com.mingche.ademospringboot.Model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*


@Entity
@Table(name = "test", schema = "test")
class Test (
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "test_id", nullable = false)
    val id: String,

    @Column(name = "test_name", nullable = false)
    val name: String,
)