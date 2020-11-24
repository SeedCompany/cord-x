package com.seedcompany.cord.dto

import com.seedcompany.cord.model.User

data class UserDto(val name: String)

data class UserCreateIn(val name: String)

data class UserCreateOut(val success: Boolean)

data class UserReadIn(val id: String)

data class UserReadOut(val user: UserDto)