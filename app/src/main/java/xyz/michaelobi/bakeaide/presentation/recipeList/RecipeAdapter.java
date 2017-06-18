package xyz.michaelobi.bakeaide.presentation.recipeList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * BakeAide
 * Michael Obi
 * 15 06 2017 2:13 PM
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipes;

    RecipeAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Recipe recipe = recipes.get(position);
        viewHolder.tvStepCount.setText(String.format(Locale.getDefault(), "%d", recipe.getSteps().size()));
        viewHolder.tvServingCount.setText(String.format(Locale.getDefault(), "%d", recipe.getServings()));
        IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredients());
        viewHolder.rvIngredients.setAdapter(ingredientAdapter);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvServingCount, tvStepCount;
        RecyclerView rvIngredients;

        ViewHolder(View itemView) {
            super(itemView);
            tvServingCount = (TextView) itemView.findViewById(R.id.tv_serve_count);
            tvStepCount = (TextView) itemView.findViewById(R.id.tv_steps_count);
            rvIngredients = (RecyclerView) itemView.findViewById(R.id.rv_ingredients);
            rvIngredients.setLayoutFrozen(true);
            rvIngredients.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Booo!", Toast.LENGTH_SHORT).show();
        }
    }
}
