package com.wanin.rd.ademospringboot.util


enum class HttpStatus(var text: String, var code: Int){
    OK("Success", 200),
    NotFound("NotFound", 404),
    BadRequest("BadRequest", 400),
}

class MessageResponse(
    val status: Int,
    val message: String,
): Response<Any>(status){

}

class PayloadResponse<T>(
    val status: Int,
    val payload: T,
): Response<T>(status){

}
open class Response<T>(
    private val status: Int,
) {

    companion object{
        fun status(status: Int): ResponseBuilder{
            return DefaultBuilder(status)
        }

        fun <T> ok(body: T): Response<T>{
            return ok().body(body)
        }

        fun ok(): ResponseBuilder{
            return status(HttpStatus.OK.code)
        }
        fun badRequest(): ResponseBuilder{
            return status(HttpStatus.BadRequest.code)
        }
        fun notFound(): ResponseBuilder{
            return status(HttpStatus.NotFound.code)
        }
    }
}

class DefaultBuilder(private val statusCode: Int): ResponseBuilder{
    private val message: String? = null
    override fun <T> body(body: T): PayloadResponse<T>{
        return PayloadResponse(
            status = this.statusCode,
            payload = body
        )
    }

    override fun body(message: String): MessageResponse{
        return MessageResponse(
            status = this.statusCode,
            message = message
        )
    }
}

interface ResponseBuilder{
    fun <T> body(body: T): PayloadResponse<T>

    fun body(message: String): MessageResponse
}


