package com.seedcompany.cord.model

// must be kept in order of increasing access
enum class Perm( val canRead: Boolean, val canEdit: Boolean) {
    NONE(canRead = false, canEdit = false),
    READ(canRead = true, canEdit = false),
    READ_WRITE(canRead = true, canEdit = true),
    READ_WRITE_DELETE(canRead = true, canEdit = true),
}