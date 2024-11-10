package com.dmforu.api.support.response

data class SuccessResponse<T> private constructor(
    val data: T? = null,
) : ApiResponse<T>() {
    companion object {
        fun success(): ApiResponse<Any> {
            return SuccessResponse("요청이 정상적으로 처리되었습니다.")
        }

        fun create(): ApiResponse<Any> {
            return SuccessResponse("정상적으로 생성되었습니다.")
        }

        fun <S> success(data: S): SuccessResponse<S> {
            return SuccessResponse(data)
        }

    }

}