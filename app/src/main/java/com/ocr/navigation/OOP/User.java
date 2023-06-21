//package com.ocr.navigation.OOP;
//
//import com.google.firebase.database.Exclude;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class User {
//    private  int id;
//    private String name;
//    private String address;
//
//    public User() {
//    }
//
//    public User(int id, String name,  String address) {
//        this.id = id;
//        this.name = name;
//
//        this.address = address;
//    }
//
//    public User(String name,  String address) {
//        this.name = name;
//
//        this.address = address;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }
//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("name", name);
//        result.put("address", address);
//
//        return result;
//    }
//}
