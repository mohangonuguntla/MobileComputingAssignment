package com.mobilecomp.recipeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Mohan Chowdary
 **/
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView txt_no_result_found, txt_search;
    private List<FoodDto> foodDtoList;
    private RecipeAdapter recipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        txt_no_result_found = findViewById(R.id.txt_no_result_found);
        txt_search = findViewById(R.id.txt_searchtext);
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });
        RecipeDatabase database = RecipeDatabase.getInstance(this);
        foodDtoList = database.Dao().getAllRecipesAsList();
        if (foodDtoList == null)
            foodDtoList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(this, foodDtoList);
        recyclerView.setAdapter(recipeAdapter);

    }

    private void filter(String text) {
        ArrayList<FoodDto> filterList = new ArrayList<>();

        for (FoodDto item: foodDtoList){
            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }

        recipeAdapter.filteredList(filterList);
    }

    public void btn_uploadActivity(View view){
        Intent i = new Intent(this,AddRecipeActivity.class);
        startActivity(i);
    }
}