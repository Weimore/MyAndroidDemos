package com.example.mymodeldemos;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mymodeldemos.databinding.ActivityProfileBindingBinding;
import com.example.mymodeldemos.viewmodel.ProfileActivityProfile;

public class ProfileActivity extends AppCompatActivity{

//    @Override
//    protected void setUpContentView() {
//        setContentView(R.layout.activity_profile,"",MODE_BACK);
//    }
    private ProfileActivityProfile profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBindingBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_profile_binding);
        ProfileActivityProfile profile = new ProfileActivityProfile();
        init(profile);
        binding.setProfileActivityProfile(profile);
    }

    private void init(ProfileActivityProfile profileActivityProfile) {
        profileActivityProfile.profileImage.set(R.drawable.rella7);
        profileActivityProfile.profileFace.set(R.mipmap.homelogo);
        profileActivityProfile.profileName.set("微啊微末");
        profileActivityProfile.profileText.set("bilibili乾杯");
    }


}
