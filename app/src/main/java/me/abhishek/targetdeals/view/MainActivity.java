package me.abhishek.targetdeals.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Scanner;

import me.abhishek.targetdeals.R;
import me.abhishek.targetdeals.model.Deal;

public class MainActivity extends FragmentActivity implements DealListFragment.DealSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new DealListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment).commit();

    }
    Scanner sc;

    @Override
    public void onDealSelected(String dealId) {
        Fragment fragment = new DealDetailFragment();
        Bundle args = new Bundle();
        args.putString(DealDetailFragment.DID_KEY, dealId);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
    }
}
