package com.egberts.jimmy.recipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<RecipeItem> recipeList;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RecipeFragment.newInstance(recipeList.get(position));
        }

        @Override
        public int getCount() {
            return recipeList.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeList = new ArrayList<>();
        getRecipes();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void getRecipes() {
        RecipeAPIClient client = RecipeAPIClient.retrofit.create(RecipeAPIClient.class);
        Call<Recipes> call = client.getRecipes(RecipeAPIClient.API_KEY, "r");
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
                if (response.isSuccessful()) {
                    Recipes recipes = response.body();
                    for (int i = 0; i < 3; i++) {
                        assert recipes != null;
                        recipeList.add(recipes.getRecipes().get(i));
                        mSectionsPagerAdapter.notifyDataSetChanged();
                    }
                } else {
                    showToast("Retrieving recipes failed!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Recipes> call, @NonNull Throwable t) {
                showToast("Connecting to external API failed!");
            }
        });
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
