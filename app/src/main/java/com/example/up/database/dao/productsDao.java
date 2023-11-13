package com.example.up.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.up.database.entities.products;

import java.util.List;

@Dao
public interface productsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(products product);
    @Update
    void update(products product);
    @Delete
    void delete(products product);

    @Query("SELECT * FROM products")
    List<products> getAllProducts();
}
