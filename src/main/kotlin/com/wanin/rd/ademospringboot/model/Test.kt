package com.wanin.rd.ademospringboot.model;

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*


@Entity
@Table(name = "test", schema = "jay")
data class Test (
    @Column(name = "test_name", nullable = false)
    val name: String,
){
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "test_id", nullable = false)
    val id: String = ""
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Test

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}