package com.mobilecomp.recipeapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FoodDto.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {


	private static RecipeDatabase instance;

	public abstract RecipeDao Dao();

	public static synchronized RecipeDatabase getInstance(Context context) {

		if (instance == null) {
			instance =

					Room.databaseBuilder(context.getApplicationContext(),
							RecipeDatabase.class, "recipe_database")
							.fallbackToDestructiveMigration()
							.addCallback(roomCallback)
							.allowMainThreadQueries()
							.build();
		}
		return instance;
	}

	private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
			new PopulateDbAsyncTask(instance).execute();
		}
	};

	private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
		PopulateDbAsyncTask(RecipeDatabase instance) {
			RecipeDao dao = instance.Dao();
		}
		@Override
		protected Void doInBackground(Void... voids) {
			return null;
		}
	}
}
