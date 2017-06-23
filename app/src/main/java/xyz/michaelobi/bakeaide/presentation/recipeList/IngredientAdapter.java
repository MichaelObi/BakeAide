package xyz.michaelobi.bakeaide.presentation.recipeList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rx.Observable;
import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.models.Ingredient;

/**
 * BakeAide
 * Michael Obi
 * 18 06 2017 8:35 AM
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
        Observable.from(ingredients)
                .take(5)
                .toList()
                .subscribe(ingredientList -> this.ingredients = ingredientList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StringBuilder stringBuilder = new StringBuilder(ingredients.get(i).getIngredient());
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        viewHolder.ingredient.setText(stringBuilder.toString());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient;

        ViewHolder(View itemView) {
            super(itemView);
            ingredient = (TextView) itemView.findViewById(R.id.ingredient);
        }
    }
}
