package com.example.up.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.up.database.entities.promotions;

import java.util.List;

@Dao
public interface promotionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(promotions promotion);
    @Update
    void update(promotions promotion);
    @Delete
    void delete(promotions promotion);

    @Query("SELECT * FROM promotions")
    List<promotions> getAllPromotions();
}
