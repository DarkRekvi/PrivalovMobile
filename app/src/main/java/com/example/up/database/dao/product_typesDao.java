package com.example.up.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.up.database.entities.product_types;

import java.util.List;

@Dao
public interface product_typesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(product_types product_type);
    @Update
    void update(product_types product_type);
    @Delete
    void delete(product_types product_type);

    @Query("SELECT * FROM product_types")
    List<product_types> getAllProductTypes();
}
