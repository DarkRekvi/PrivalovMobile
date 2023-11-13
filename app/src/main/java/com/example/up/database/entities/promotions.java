package com.example.up.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "promotions")
public class promotions {
    @PrimaryKey
    public long genre_id;
    @ColumnInfo
    public String genre_name;
    public promotions(){}
}

