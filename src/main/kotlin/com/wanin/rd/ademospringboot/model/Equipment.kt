package com.wanin.rd.ademospringboot.model;

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "equipments", schema = "jay")
data class Equipment (
    /*
    * IDENTITY 依照資料庫進行auto增加
    * AUTO 不指定生成配置，由程式碼產生
    * TABLE：使用一个特定的数据库表格来保存主键。
    * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id", nullable = false)
    val id: Int,

    @Column(name = "equipment_type", nullable = false)
    val type: String,

    @Column(name = "equipment_name", nullable = false)
    val name: String,

    @Column(name = "equipment_attribute", nullable = false)
    val attribute: String,

    @Column(name = "equipment_value", nullable = false)
    val value: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Equipment

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
