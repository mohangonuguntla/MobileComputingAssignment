package com.mobilecomp.recipeapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

	private RecipeDao dao;
	private LiveData<List<FoodDto>> allRecipes;
	public RecipeRepository(Application application) {
		RecipeDatabase database = RecipeDatabase.getInstance(application);
		dao = database.Dao();
		allRecipes = dao.getAllRecipes();
	}

	public void insert(FoodDto model) {
		new InsertCourseAsyncTask(dao).execute(model);
	}

	public void update(FoodDto model) {
		new UpdateCourseAsyncTask(dao).execute(model);
	}

	public void delete(FoodDto model) {
		new DeleteCourseAsyncTask(dao).execute(model);
	}

	public void deleteAllCourses() {
		new DeleteAllCoursesAsyncTask(dao).execute();
	}

	public LiveData<List<FoodDto>> getAllCourses() {
		return allRecipes;
	}

	private static class InsertCourseAsyncTask extends AsyncTask<FoodDto, Void, Void> {
		private RecipeDao dao;

		private InsertCourseAsyncTask(RecipeDao dao) {
			this.dao = dao;
		}

		@Override
		protected Void doInBackground(FoodDto... model) {
			dao.save(model[0]);
			return null;
		}
	}

	private static class UpdateCourseAsyncTask extends AsyncTask<FoodDto, Void, Void> {
		private RecipeDao dao;

		private UpdateCourseAsyncTask(RecipeDao dao) {
			this.dao = dao;
		}

		@Override
		protected Void doInBackground(FoodDto... models) {
			dao.update(models[0]);
			return null;
		}
	}

	// we are creating a async task method to delete course.
	private static class DeleteCourseAsyncTask extends AsyncTask<FoodDto, Void, Void> {
		private RecipeDao dao;
		private DeleteCourseAsyncTask(RecipeDao dao) {
			this.dao = dao;
		}

		@Override
		protected Void doInBackground(FoodDto... models) {
			dao.delete(models[0]);
			return null;
		}
	}

	private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
		private RecipeDao dao;
		private DeleteAllCoursesAsyncTask(RecipeDao dao) {
			this.dao = dao;
		}
		@Override
		protected Void doInBackground(Void... voids) {
			dao.deleteAllRecipes();
			return null;
		}
	}
}
