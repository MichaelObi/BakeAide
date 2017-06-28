package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.models.Ingredient;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.bakeaide.data.models.Step;
import xyz.michaelobi.bakeaide.databinding.FragmentRecipeDetailsBinding;
import xyz.michaelobi.bakeaide.presentation.recipeStep.RecipeStepActivity;
import xyz.michaelobi.bakeaide.presentation.recipeStep.RecipeStepFragment;

/**
 * BakeAide
 * Michael Obi
 * 24 06 2017 7:16 PM
 */

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsMvpContract.View {
    RecipeDetailsMvpContract.Presenter presenter;
    FragmentRecipeDetailsBinding binding;
    RecipeStepListAdapter recipeStepListAdapter;
    boolean tabletView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_details,
                container, false);
        View view = binding.getRoot();
        tabletView = binding.recipeStepContainer != null;
        presenter = new RecipeDetailsPresenter();
        presenter.attachView(this);
        Recipe recipe = getActivity().getIntent().getParcelableExtra("recipe");
        if (recipe == null) {
            getActivity().finish();
        }
        recipeStepListAdapter = new RecipeStepListAdapter(step -> presenter.showRecipeStep(step));
        binding.recipeDetailsSteps.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recipeDetailsSteps.setHasFixedSize(true);
        binding.recipeDetailsSteps.setAdapter(recipeStepListAdapter);
        presenter.setupRecipeDetails(recipe);
        return view;
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();
        String header = "Ingredients:";
        sb.append(header);

        for (Ingredient ingredient : ingredients) {
            String name = ingredient.getIngredient();
            float quantity = ingredient.getQuantity();
            String measure = ingredient.getMeasure();
            sb.append("\n");
            String line = getActivity().getResources().getString(R.string.recipe_details_ingredient_item);

            String quantityStr = String.format(Locale.US, "%s", quantity);
            if (quantity == (long) quantity) {
                quantityStr = String.format(Locale.US, "%d", (long) quantity);
            }
            sb.append(String.format(Locale.getDefault(), line, name, quantityStr, measure));
        }
        String text = sb.toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        int start = text.indexOf(header);
        int end = start + header.length();
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        binding.recipeDetailsIngredients.setText(spannableStringBuilder);
    }

    @Override
    public void showStepList(List<Step> steps) {
        recipeStepListAdapter.setStepList(steps);
        if (tabletView) {
            presenter.loadStepDetails(steps.get(0));
        }
    }

    @Override
    public void displayStep(Step step) {
        if (!tabletView) {
            Intent intent = new Intent(getActivity(), RecipeStepActivity.class);
            intent.putExtra("step", step);
            getActivity().startActivity(intent);
        } else {
            RecipeStepFragment recipeStepFragment = RecipeStepFragment.newInstance(step);
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.recipe_step_container, recipeStepFragment);
            transaction.commit();
        }
    }
}
