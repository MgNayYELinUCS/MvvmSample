package com.example.mvvmsample.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.db.entities.CURRENT_USER_ID
import com.example.mvvmsample.data.network.model.AuthResponse.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User):Long

    @Query("SELECT * FROM user WHERE uid= $CURRENT_USER_ID")
    fun getUser():LiveData<User>
}