package me.abhishek.targetdeals.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import me.abhishek.targetdeals.R;

public class NetworkUtils {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void showNoInternet(View container) {
        Snackbar mySnackbar = Snackbar.make(container,
                R.string.no_internet, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

}
