package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.michaelobi.bakeaide.data.models.Step;
import xyz.michaelobi.bakeaide.databinding.RecipeStepListItemBinding;

/**
 * BakeAide
 * Michael Obi
 * 25 06 2017 3:21 PM
 */

public final class RecipeStepListAdapter extends RecyclerView.Adapter<RecipeStepListAdapter.ViewHolder> {

    private final OnStepClickListener stepClickListener;
    private List<Step> steps;

    RecipeStepListAdapter(OnStepClickListener stepClickListener) {
        this.stepClickListener = stepClickListener;
        steps = new ArrayList<>(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecipeStepListItemBinding itemBinding = RecipeStepListItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Step step = steps.get(i);
        viewHolder.stepBinding.listStepDescription.setText(step.getShortDescription());
        viewHolder.itemView.setOnClickListener(v -> stepClickListener.onStepClick(step));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void setStepList(List<Step> stepList) {
        steps = stepList;
        notifyDataSetChanged();
    }

    interface OnStepClickListener {
        void onStepClick(Step step);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final RecipeStepListItemBinding stepBinding;

        ViewHolder(RecipeStepListItemBinding stepBinding) {
            super(stepBinding.getRoot());
            this.stepBinding = stepBinding;
        }
    }
}
