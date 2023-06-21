package com.ocr.navigation.dataLocal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ocr.navigation.OOP.Product;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void insertFavourite(Product product);

    @Query( "SELECT*FROM productFavorite" )
    List<Product> getListFavourite();


    @Delete
    void deleteFavourite(Product product);
}
