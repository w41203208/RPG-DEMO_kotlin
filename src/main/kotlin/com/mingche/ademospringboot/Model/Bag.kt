package com.mingche.ademospringboot.Model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "bag", schema = "Test")
class Bag (
    /*
    * 設定ID初始化使用 uuid 產生ID
    * */
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "bag_id")
    val id: Int,

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = true)
    val userId: Int,

    @ManyToOne(optional = false)
    @Column(name= "equipment_id")
    val equipmentId: Int,


)