package com.example.mymodeldemos.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

/**
 * Created by 吴城林 on 2017/9/16.
 */

public class ProfileActivityProfile {
    public ObservableInt profileImage = new ObservableInt();
    public ObservableInt profileFace = new ObservableInt();
    public ObservableField<String> profileName = new ObservableField<>();
    public ObservableField<String> profileText = new ObservableField<>();

    @BindingAdapter("bg")
    public static void steBg(ImageView imageView,int resource){
        imageView.setBackgroundResource(resource);
    }

    public void buttonClick(View v){
        profileName.set("dhfskhdfskhf");
        profileText.set("fdfdfd");
    }
}
