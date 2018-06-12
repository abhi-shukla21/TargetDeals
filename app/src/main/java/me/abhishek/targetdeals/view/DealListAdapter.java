package me.abhishek.targetdeals.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.abhishek.targetdeals.R;
import me.abhishek.targetdeals.databinding.DealListItemBinding;
import me.abhishek.targetdeals.model.Deal;

public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.DealViewHolder> {

    private List<Deal> mDeals;

    public DealListAdapter() {
        setDeals(new ArrayList<Deal>());
    }

    public void setDeals(List<Deal> deals) {
        mDeals = deals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DealListItemBinding binding = DealListItemBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new DealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        Deal deal = mDeals.get(position);
        holder.bind(deal);

    }

    @Override
    public int getItemCount() {
        return mDeals.size();
    }

    class DealViewHolder extends RecyclerView.ViewHolder {
        DealListItemBinding binding;

        public DealViewHolder(DealListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Deal deal) {
            binding.setDeal(deal);
            binding.executePendingBindings();
        }
    }
}
