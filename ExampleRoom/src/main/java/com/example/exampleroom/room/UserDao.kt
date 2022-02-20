package com.example.exampleroom.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Data Access Object
 * 数据访问对象 (DAO)，提供您的应用可用于查询、更新、插入和删除数据库中的数据的方法。
 * 数据库类为应用提供与该数据库关联的 DAO 的实例。
 * 反过来，应用可以使用 DAO 从数据库中检索数据，作为关联的数据实体对象的实例。
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}