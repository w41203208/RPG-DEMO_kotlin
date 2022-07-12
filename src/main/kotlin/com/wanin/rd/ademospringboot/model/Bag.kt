package com.wanin.rd.ademospringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "bag", schema = "jay")
data class Bag (

    /**
     * JsonIgnore 在取得 Bag JSON 資料時不取得此欄位，加這段可以防止無限迴圈，因為 User 參照到 Bag 而 Bag 裡面又參照到 User 這樣會有迴圈
     * FetchType：EAGER 當query的時候即時傳回，LAZY 當query的時候有.get()才有傳回
     * */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    val bagOfUser: User,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id", nullable = false)
    val equipment: Equipment,
){
    /**
     *
     * 設定ID初始化使用 uuid 產生ID
     * */
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "bag_id")
    val id: String = ""
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Bag

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}