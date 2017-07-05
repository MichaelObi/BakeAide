package xyz.michaelobi.bakeaide.presentation.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Locale;

import xyz.michaelobi.bakeaide.Injector;
import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.local.LocalRecipeRepository;
import xyz.michaelobi.bakeaide.data.models.Ingredient;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.bakeaide.presentation.recipeDetails.RecipeDetailsActivity;


/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        LocalRecipeRepository localRecipeRepository = Injector.getLocalRecipeRepository();
        Recipe recipe = localRecipeRepository.getLastAccessedRecipe();
        if (recipe == null) {
            return;
        }
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra("recipe", recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);
        views.setTextViewText(R.id.recipe_name, recipe.getName());
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : recipe.getIngredients()) {
            String name = ingredient.getIngredient();
            float quantity = ingredient.getQuantity();
            String measure = ingredient.getMeasure();
            sb.append("\n");
            String line = context.getResources().getString(R.string.recipe_details_ingredient_item);
            String quantityStr = String.format(Locale.US, "%s", quantity);
            if (quantity == (long) quantity) {
                quantityStr = String.format(Locale.US, "%d", (long) quantity);
            }
            sb.append(String.format(Locale.getDefault(), line, name, quantityStr, measure));
        }
        views.setTextViewText(R.id.recipe_details_ingredients, sb.toString());
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
}

