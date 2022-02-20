package com.example.examplepaging.paging

import androidx.room.Entity

@Entity(tableName = "users")
data class User(val id: Int, val label: String)