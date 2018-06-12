package me.abhishek.targetdeals.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import me.abhishek.targetdeals.R;
import me.abhishek.targetdeals.utils.NetworkUtils;
import me.abhishek.targetdeals.di.module.RepositoryModule;
import me.abhishek.targetdeals.di.component.DaggerDealListComponent;
import me.abhishek.targetdeals.model.Deal;
import me.abhishek.targetdeals.utils.RequestStatus;
import me.abhishek.targetdeals.viewmodel.DealListViewModel;
import me.abhishek.targetdeals.viewmodel.factory.DealListViewModelFactory;

public class DealListFragment extends Fragment implements Observer<List<Deal>>,
        RecyclerItemClickListener.OnItemClickListener, View.OnClickListener {

    private DealListViewModel viewModel;
    private DealSelectedListener mListener;

    @Inject
    DealListViewModelFactory viewModelFactory;

    private DealListAdapter mAdapter = new DealListAdapter();
    private List<Deal> mDeals;
    private ProgressDialog progressDialog;
    private View errorView;
    private View retryView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof DealSelectedListener)) {
            throw new IllegalArgumentException("Activity must implement DealSelectedListener");
        }
        mListener = (DealSelectedListener) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDealListComponent.builder().repositoryModule
                (new RepositoryModule(getActivity().getApplication())).build().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.deal_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.rv_deal_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), this));
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        errorView = root.findViewById(R.id.tv_error);
        retryView = root.findViewById(R.id.btn_retry);
        retryView.setOnClickListener(this);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DealListViewModel.class);
        requestDeals();

    }

    private void requestDeals() {
        MutableLiveData<RequestStatus> requestStatus = new MutableLiveData<>();
        requestStatus.observe(this, requestStatusObserver);
        if (mDeals == null || mDeals.isEmpty()) {
            requestStatus.setValue(RequestStatus.LOADING);
        }
        viewModel.getDeals(requestStatus).observe(this, this);
        hideErrorView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!NetworkUtils.isOnline(getContext())) {
            NetworkUtils.showNoInternet(getView());
        }
    }

    @Override
    public void onChanged(@Nullable List<Deal> deals) {
        mDeals = deals;
        mAdapter.setDeals(deals);
        if (mDeals != null && mDeals.size() > 0) {
            if (progressDialog.isShowing()) {
                progressDialog.hide();
            }
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        if (mListener != null) {
            mListener.onDealSelected(mDeals.get(position).get_id());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retry:
                requestDeals();
                break;
        }
    }


    private Observer<RequestStatus> requestStatusObserver = new Observer<RequestStatus>() {
        @Override
        public void onChanged(@Nullable RequestStatus requestStatus) {
            switch (requestStatus) {
                case LOADING:
                    if (!progressDialog.isShowing()) {
                        progressDialog.show();
                    }
                    break;
                case FAILED:
                    if (mDeals == null || mDeals.isEmpty()) {
                        showErrorView();
                    }
                case SUCCESS:
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    break;


            }
        }
    };

    private void showErrorView() {
        errorView.setVisibility(View.VISIBLE);
        retryView.setVisibility(View.VISIBLE);
    }

    private void hideErrorView() {
        errorView.setVisibility(View.GONE);
        retryView.setVisibility(View.GONE);
    }


    public interface DealSelectedListener {
        void onDealSelected(String dealId);
    }
}
