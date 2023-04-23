package com.mobilecomp.recipeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class AddRecipeActivity extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_description, txt_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_activity);

        recipeImage = findViewById(R.id.iv_foodImage);
        txt_name = findViewById(R.id.txt_recipe_name);
        txt_description = findViewById(R.id.text_description);
        txt_price = findViewById(R.id.text_price);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            uri = data.getData();
            recipeImage.setImageURI(uri);
        }
        else {
            Snackbar.make(findViewById(R.id.txt_recipe_name),"You haven't picked image", BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }


    public void btnUploadRecipe(View view) {

        if(uri == null ){
            Snackbar.make(view,"Please Upload Image",Snackbar.LENGTH_LONG).show();
        }
        else if(txt_name.getText().toString().isEmpty()){
            Snackbar.make(view,"Please Write Recipe Name",Snackbar.LENGTH_LONG).show();
        }
        else if(txt_description.getText().toString().isEmpty()){
            Snackbar.make(view,"Please Write Recipe Description",Snackbar.LENGTH_LONG).show();
        }
        else {
            FoodDto dto = new FoodDto();
            dto.setItemDescription(txt_description.getText().toString());
            dto.setItemImage(uri.toString());
            dto.setItemName(txt_name.getText().toString());
            RecipeDatabase database = RecipeDatabase.getInstance(this);
            long id = database.Dao().save(dto);
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }

}