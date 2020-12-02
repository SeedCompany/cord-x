package com.seedcompany.cord.dto

enum class ErrorCode(val code: Number) {
    NO_ERROR(0),
    UNKNOWN_ERROR(1),
    ID_NOT_FOUND(2),
    UNIQUENESS_VIOLATION(3),
}