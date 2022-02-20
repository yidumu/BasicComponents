package com.example.exampleroom.room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Ignore

/**
 * 数据实体，用于表示应用的数据库中的表。
 */
//默认情况向表的名称是类名
@Entity(tableName = "users")
data class User(
    //表包含一个主键和多个列，每个表必须有一个主键
    @PrimaryKey val uid: Int,
    //默认字段名称是列的名词
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    //忽略字段
    @Ignore val picture: Bitmap?
)