package com.egberts.jimmy.recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipes {

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("recipes")
    @Expose
    private List<RecipeItem> recipes;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<RecipeItem> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeItem> recipes) {
        this.recipes = recipes;
    }
}
