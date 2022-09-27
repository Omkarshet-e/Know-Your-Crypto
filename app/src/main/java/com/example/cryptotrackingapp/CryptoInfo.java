package com.example.cryptotrackingapp;

public class CryptoInfo {
    String name;
    String symbol;
    String is_active;
    String id;

    public CryptoInfo(String name, String symbol, String is_active, String id) {
        this.name = name;
        this.symbol = symbol;
        this.is_active = is_active;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
