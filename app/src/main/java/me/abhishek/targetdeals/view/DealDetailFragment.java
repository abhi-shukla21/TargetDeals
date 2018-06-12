package me.abhishek.targetdeals.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.abhishek.targetdeals.R;
import me.abhishek.targetdeals.di.module.RepositoryModule;
import me.abhishek.targetdeals.di.component.DaggerDealDetailComponent;
import me.abhishek.targetdeals.model.Deal;
import me.abhishek.targetdeals.viewmodel.DealDetailViewModel;
import me.abhishek.targetdeals.viewmodel.factory.DealDetailViewModelFactory;

public class DealDetailFragment extends Fragment implements Observer<Deal> {

    public static final String DID_KEY = "did";
    private DealDetailViewModel viewModel;
    private me.abhishek.targetdeals.databinding.DealDetailBinding binding;

    @Inject
    DealDetailViewModelFactory dealDetailViewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDealDetailComponent.builder().repositoryModule(new RepositoryModule(getActivity().getApplication())).build().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.deal_detail, container, false);
        binding.tvDealDesc.setMovementMethod(new ScrollingMovementMethod());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String dealId = getArguments().getString(DID_KEY);
        viewModel = ViewModelProviders.of(this, dealDetailViewModelFactory).get(DealDetailViewModel.class);
        viewModel.init(dealId);
        viewModel.getDeal().observe(this, this);
    }


    @Override
    public void onChanged(@Nullable Deal deal) {
        if (deal != null) {
            binding.setDeal(deal);
        }

    }


}
