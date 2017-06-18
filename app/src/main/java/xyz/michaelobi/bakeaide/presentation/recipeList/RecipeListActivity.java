package xyz.michaelobi.bakeaide.presentation.recipeList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.components.recyclerview.decorator.LinearSpaceDecorator;
import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * BakeAide
 * Michael Obi
 * 14 06 2017 11:40 PM
 */

public class RecipeListActivity extends AppCompatActivity implements RecipeListMvpContract.View {


    private Toolbar toolbar;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewRecipes;
    private TextView textViewErrorMessage;
    private RecipeListPresenter presenter;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textViewErrorMessage = (TextView) findViewById(R.id.text_view_error_msg);
        recyclerViewRecipes = (RecyclerView) findViewById(R.id.rv_recipes);
        recyclerViewRecipes.addItemDecoration(new LinearSpaceDecorator(16));
        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));
        presenter = new RecipeListPresenter();
        presenter.attachView(this);
        presenter.getRecipes();
        recipeAdapter = new RecipeAdapter();
        recyclerViewRecipes.setAdapter(recipeAdapter);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recyclerViewRecipes.setVisibility(View.VISIBLE);
        textViewErrorMessage.setVisibility(View.GONE);
        recipeAdapter.setRecipes(recipes);
    }

    @Override
    public void showError(String error) {
        textViewErrorMessage.setVisibility(View.VISIBLE);
        recyclerViewRecipes.setVisibility(View.GONE);
        textViewErrorMessage.setText(error);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewRecipes.setVisibility(View.GONE);
        textViewErrorMessage.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerViewRecipes.setVisibility(View.VISIBLE);
        textViewErrorMessage.setVisibility(View.GONE);
    }
}
