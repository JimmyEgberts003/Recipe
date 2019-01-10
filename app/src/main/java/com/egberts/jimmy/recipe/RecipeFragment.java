package com.egberts.jimmy.recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeFragment extends Fragment {
    private RecipeItem recipe;

    public static RecipeFragment newInstance(RecipeItem recipe) {
        RecipeFragment fragment = new RecipeFragment();
        fragment.setRecipe(recipe);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView recipeName = rootView.findViewById(R.id.recipeName);
        recipeName.setText(recipe.getTitle());

        ImageView recipeImage = rootView.findViewById(R.id.recipeImage);
        System.out.println((recipe.getImageUrl()));
        Glide.with(this).load(recipe.getImageUrl()).into(recipeImage);

        return rootView;
    }

    public void setRecipe(RecipeItem recipe) {
        this.recipe = recipe;
    }
}
