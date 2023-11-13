package com.example.up.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.up.database.entities.product_statuses;

import java.util.List;

@Dao
public interface product_statusesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(product_statuses product_status);
    @Update
    void update(product_statuses product_status);
    @Delete
    void delete(product_statuses product_status);

    @Query("SELECT * FROM product_statuses")
    List<product_statuses> getAllProductStatuses();
}
