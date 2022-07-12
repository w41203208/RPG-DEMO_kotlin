package com.wanin.rd.ademospringboot.util


class Response<T>(
    val state: String,
    val payload: T,
    val message: String? = null,
) {

}