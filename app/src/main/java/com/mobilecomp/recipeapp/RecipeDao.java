package com.mobilecomp.recipeapp;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert
    long save(FoodDto model);
    @Update
    void update(FoodDto model);
    @Query("SELECT * FROM FoodDto ORDER BY itemName ASC")
    LiveData<List<FoodDto>> getAllRecipes();

    @Query("SELECT * FROM FoodDto ORDER BY itemName ASC")
    List<FoodDto> getAllRecipesAsList();
    @Delete
    void delete(FoodDto dto);

    @Query("DELETE FROM FoodDto")
    void deleteAllRecipes();

}
