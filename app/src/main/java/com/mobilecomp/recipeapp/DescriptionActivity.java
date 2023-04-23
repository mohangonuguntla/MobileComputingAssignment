package com.mobilecomp.recipeapp;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class DescriptionActivity extends AppCompatActivity {

    TextView foodDescription, recipeName, recipePrice;
    ImageView foodImage, like_imageView;
    Button btnUpdate, btnDelete;
    String recipeItemName, recipeItemPrice, recipeDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        recipeName = findViewById(R.id.txtRecipeName);
        recipePrice = findViewById(R.id.txtPrice);
        foodDescription = findViewById(R.id.txtDescription);
        foodImage = findViewById(R.id.ivImage2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        like_imageView = findViewById(R.id.like_imageView);


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {
            recipeItemName = mBundle.getString("RecipeName");
            recipeName.setText(recipeItemName);
            recipeItemPrice = mBundle.getString("price");
            recipePrice.setText(recipeItemPrice);
            recipeDescription = mBundle.getString("Description");
            foodDescription.setText(recipeDescription);
            Glide.with(this).load(Uri.parse(mBundle.getString("Image"))).into(foodImage);
        }


    }

    public void btnDeleteRecipe(View view) {

    }

    public void btnUpdateRecipe(View view) {

//        startActivity(new Intent(getApplicationContext(),UpdateRecipeActivity.class)
//                .putExtra("recipeNameKey", recipeName.getText().toString())
//                .putExtra("descriptionKey",foodDescription.getText().toString())
//                .putExtra("priceKey",recipePrice.getText().toString())
//                .putExtra("key",key)
//                .putExtra("recipeKey",recipeKey));
    }

    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}