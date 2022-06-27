package com.mingche.ademospringboot.Model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDate.now
import javax.persistence.*


@Entity
@Table(name = "users", schema = "test")
class User(

    /*
    * 設定ID初始化使用 uuid 產生ID
    * */
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "user_id", nullable = false)
    val id: String,

    @Column(name = "user_name", nullable = false)
    val name: String,

    @Column(name = "user_password", nullable = false)
    val password: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDate = now(),

    @Column(name = "updated_at", nullable = false)
    val updateAt: LocalDate = now(),

//    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @JoinColumn(name = "equipment_body", referencedColumnName = "equipment_id",nullable = true)
//    val body_slot: Int? = null,
//
//    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @JoinColumn(name = "equipment_hand", referencedColumnName = "equipment_id",nullable = true)
//    val hand_slot: Int? = null,
//
//
////    @JoinTable(name = "bag",
////                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id"),
////                                JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id")])
//    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    val userBag: MutableList<Bag>? = mutableListOf()
)