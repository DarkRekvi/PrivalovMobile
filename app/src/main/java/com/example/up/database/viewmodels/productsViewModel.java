package com.example.up.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.up.database.Database;
import com.example.up.database.entities.products;

import java.util.List;

public class productsViewModel extends AndroidViewModel {
    private Database database;
    public List<products> products;
    public productsViewModel(@NonNull Application application){
        super(application);
        database = Database.getDatabase(getApplication());
    }

    public void addProducts(products item){
        Runnable addProductsRnb = ()->{
            database.productsDao().insert(item);
        };
        Thread thread = new Thread(addProductsRnb);
        thread.start();
    }

    public void deleteProducts(products item){
        Runnable deleteProductsRnb = ()->{
            database.productsDao().delete(item);
        };
        Thread thread = new Thread(deleteProductsRnb);
        thread.start();
    }

    public void updateProducts(products item){
        Runnable updateProductsRnb = ()->{
            database.productsDao().update(item);
        };
        Thread thread = new Thread(updateProductsRnb);
        thread.start();
    }
}
