package com.example.up.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "products")
public class products {
    @PrimaryKey(autoGenerate = true)
    public long product_id;
    @ColumnInfo
    public String product_name;
    @ColumnInfo
    public String product_promotion;
    @ColumnInfo
    public String product_status;
    @ColumnInfo
    public String product_price;
    @ColumnInfo
    public long product_type;
    @ColumnInfo
    public String product_price_category;
    public products(){}
}
