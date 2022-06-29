package com.mingche.ademospringboot.Dto

class UserDTO {
    class Registrant(
        val name: String,
        val password: String,
    )
    class AddingItemInBag(
        val uid: String,
        val eid: Int,
    )
    class UserData(
        val id: String,
        val name: String,
    )
    class UpdatingUserData(
        val id: String,
        val name: String,
        val password: String,
    )

}