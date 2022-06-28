//package com.mingche.ademospringboot.Model
//
//import org.hibernate.annotations.GenericGenerator
//import javax.persistence.*
//
//@Entity
//@Table(name = "bag", schema = "Test")
//class Bag (
//    /*
//    * 設定ID初始化使用 uuid 產生ID
//    * */
//    @Id
//    @GeneratedValue(generator = "uuidGenerator")
//    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
//    @Column(name = "bag_id")
//    val id: Int,
//
//    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
//    val userId: Int,
//
//    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @JoinColumn(name= "equipment_id", referencedColumnName = "equipment_id", nullable = false)
//    val equipmentId: Int,
//
//)