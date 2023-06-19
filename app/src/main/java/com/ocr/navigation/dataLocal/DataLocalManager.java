package com.ocr.navigation.dataLocal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ocr.navigation.OOP.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataLocalManager {
    private static final String PREF_OBJECT_PRODUCT_FAVOURITE = "object_favourite";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static boolean isFavorite(int productID) {
        List<Product> favoriteProducts = getFavoriteProducts();
        if (favoriteProducts != null) {
            // Kiểm tra xem sản phẩm có tồn tại trong danh sách yêu thích dựa trên productID
            for (Product product : favoriteProducts) {
                if (product.getProduct_id() == productID) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addFavorite(Product product) {
        List<Product> favoriteProducts = getFavoriteProducts();
        if (favoriteProducts == null) {
            favoriteProducts = new ArrayList<>();
        }
        favoriteProducts.add(product);
        saveFavoriteProducts(favoriteProducts);
    }

    public static void removeFavorite(int productID) {
        List<Product> favoriteProducts = getFavoriteProducts();
        if (favoriteProducts != null) {
            // Tìm và xóa sản phẩm khỏi danh sách yêu thích dựa trên productID
            Iterator<Product> iterator = favoriteProducts.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getProduct_id() == productID) {
                    iterator.remove();
                    break;
                }
            }
            saveFavoriteProducts(favoriteProducts);
        }
    }

    public static List<Product> getFavoriteProducts() {
        String strJsonFavourite = DataLocalManager.getInstance().mySharedPreferences.getStringFavourite(PREF_OBJECT_PRODUCT_FAVOURITE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(strJsonFavourite, type);
    }

    private static void saveFavoriteProducts(List<Product> favoriteProducts) {
        Gson gson = new Gson();
        String strJsonFavourite = gson.toJson(favoriteProducts);
        DataLocalManager.getInstance().mySharedPreferences.pustStringFavourite(PREF_OBJECT_PRODUCT_FAVOURITE, strJsonFavourite);
    }
}