package com.example.up.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.up.database.entities.price_categories;
import com.example.up.database.entities.product_statuses;

import java.util.List;

@Dao
public interface price_categoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(price_categories price_category);
    @Update
    void update(price_categories price_category);
    @Delete
    void delete(price_categories price_category);

    @Query("SELECT * FROM price_categories")
    List<product_statuses> getAllPriceCategories();
}
