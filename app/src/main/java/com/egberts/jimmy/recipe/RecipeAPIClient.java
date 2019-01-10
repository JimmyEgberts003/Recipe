package com.egberts.jimmy.recipe;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeAPIClient {
    String BASE_URL = "https://www.food2fork.com/api/";
    String API_KEY = "f339ade47d4d1c7d976765a3df2e4e6d";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("search")
    Call<Recipes> getRecipes(@Query("key") String key, @Query("sort") String sort);
}
