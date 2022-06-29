package com.mingche.ademospringboot.Model

import javax.persistence.*

@Entity
@Table(name = "equipments", schema = "test")
class Equipment (
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
)
