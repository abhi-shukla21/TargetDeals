package me.abhishek.targetdeals.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import me.abhishek.targetdeals.R;

public class BindingUtils {

    private static RequestOptions requestOptions;

    static {
        requestOptions = new RequestOptions().placeholder(android.R.color.darker_gray).centerInside();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load("https://picsum.photos/200/300/?random").apply(requestOptions).into(imageView);
    }


    @BindingAdapter("strikePrice")
    public static void setStrikePrice(TextView textView, String price) {
        String formattedString = String.format(textView.getContext().getString(R.string.mrp_format), price);
        textView.setText(formattedString, TextView.BufferType.SPANNABLE);
        Spannable spannable = (Spannable) textView.getText();
        spannable.setSpan(new StrikethroughSpan(), 5, formattedString.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

    }
}
