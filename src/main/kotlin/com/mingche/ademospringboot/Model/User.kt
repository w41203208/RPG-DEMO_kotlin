package com.mingche.ademospringboot.Model



import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDate.now
import javax.persistence.*


@Entity
@Table(name = "users", schema = "test")
class User(
    @Column(name = "user_name", nullable = false)
    var name: String,

    @Column(name = "user_password", nullable = false)
    var password: String,

    /**
     * 每個User都有一個身體裝備槽，與Equipment是1對1關係
     * */
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_body", referencedColumnName = "equipment_id", nullable = true)
    val body_slot: Equipment? = null,

    /**
     * 每個User都有一個手部裝備槽，與Equipment是1對1關係
     * */
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_hand", referencedColumnName = "equipment_id",nullable = true)
    val hand_slot: Equipment? = null, // 這裡外鍵在資料庫是以 Int，但在這裡是屬性要用你關聯的 model

    /**
     * 每個User都有背包，與Equipment是多對多關係，這裡會是與Bag為1對多
     * */
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "bagOfUser")
    val user_Bag: MutableList<Bag> = mutableListOf()
){
    /**
     * 設定ID初始化使用 uuid 產生ID
     */
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "user_id", nullable = false)
    val id: String = ""

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDate = now()

    @Column(name = "updated_at", nullable = false)
    var updateAt: LocalDate = now()


}