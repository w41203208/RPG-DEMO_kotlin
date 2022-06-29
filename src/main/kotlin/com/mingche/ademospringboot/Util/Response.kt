package com.mingche.ademospringboot.Util


class Response<T>(
    val state: String,
    val payload: T,
    val message: String? = null,
) {

}