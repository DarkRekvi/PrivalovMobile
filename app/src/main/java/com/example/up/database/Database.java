package com.example.up.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.up.database.dao.product_statusesDao;
import com.example.up.database.dao.price_categoriesDao;
import com.example.up.database.dao.product_typesDao;
import com.example.up.database.dao.productsDao;
import com.example.up.database.dao.promotionsDao;
import com.example.up.database.entities.price_categories;
import com.example.up.database.entities.product_statuses;
import com.example.up.database.entities.product_types;
import com.example.up.database.entities.products;
import com.example.up.database.entities.promotions;

@androidx.room.Database(entities = {product_statuses.class, products.class, product_types.class, promotions.class, price_categories.class}, version = 2)
public abstract class Database extends RoomDatabase {
    public abstract productsDao productsDao();
    public abstract product_statusesDao product_statusDao();
    public abstract product_typesDao product_typeDao();
    public abstract promotionsDao promotionDao();
    public abstract price_categoriesDao pcDao();
    public static volatile Database INSTANCE;
    public static Database getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            Database.class,
                            "my_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
