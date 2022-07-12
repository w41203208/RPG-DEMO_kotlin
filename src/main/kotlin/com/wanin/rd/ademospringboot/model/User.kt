package com.wanin.rd.ademospringboot.model;



import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDate.now
import javax.persistence.*


@Entity
@Table(name = "users", schema = "jay")
data class User(
    @Column(name = "user_name", nullable = false)
    var name: String,

    @Column(name = "user_password", nullable = false)
    var password: String,

    /**
     * 每個User都有一個身體裝備槽，與Equipment是1對1關係
     * */
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_body", referencedColumnName = "equipment_id", nullable = true)
    var body_slot: Equipment? = null,

    /**
     * 每個User都有一個手部裝備槽，與Equipment是1對1關係
     * */
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_hand", referencedColumnName = "equipment_id",nullable = true)
    var hand_slot: Equipment? = null, // 這裡外鍵在資料庫是以 Int，但在這裡是屬性要用你關聯的 model

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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

}