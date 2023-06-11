package com.ocr.navigation.my_interface;

import android.view.View;

import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.OOP.ProductList;

public interface ClickItemProduc {
    void onItemProductClick(Product product);
    void onClickFavoriteItem(int pos);

}
