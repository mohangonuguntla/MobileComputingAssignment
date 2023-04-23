package com.mobilecomp.recipeapp;

import android.net.Uri;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class FoodDto {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String itemName;
    private String itemDescription;
    private String itemImage;

    public FoodDto() {

    }

    @Ignore
    public FoodDto(String itemName, String itemDescription, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
    }

    @Ignore
    public FoodDto(int id, String itemDescription, String itemName, String itemImage){
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }


    public String getItemImage() {
        return itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
