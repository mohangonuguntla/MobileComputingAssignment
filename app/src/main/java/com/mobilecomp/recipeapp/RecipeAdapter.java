package com.mobilecomp.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context mContext;
    private List<FoodDto> myFoodList;
    private int lastPosition = -1;



    public RecipeAdapter(Context mContext, List<FoodDto> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item, parent, false);
        return new FoodViewHolder(itemView);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position) {
        Glide.with(mContext).load(Uri.parse(myFoodList.get(position).getItemImage())).into(holder.imageView);


        holder.mTitle.setText(myFoodList.get(position).getItemName());
        holder.mDescription.setText(myFoodList.get(position).getItemDescription());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(mContext,DescriptionActivity.class);
                intent.putExtra("RecipeName",myFoodList.get(holder.getAdapterPosition()).getItemName());
                intent.putExtra("Image",myFoodList.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myFoodList.get(holder.getAdapterPosition()).getItemDescription());
                mContext.startActivity(intent);
            }
        });

        setAnimation(holder.itemView,position);
    }

    public void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodDto> filterList) {
        myFoodList = filterList;
        notifyDataSetChanged();
    }

}

class FoodViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView mTitle, mDescription;
    CardView mCardView;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);

        mCardView = itemView.findViewById(R.id.myCardView);
    }
}