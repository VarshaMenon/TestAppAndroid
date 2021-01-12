package com.example.androidtestapp.view.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.androidtestapp.R;
import com.example.androidtestapp.databinding.ActivityDetailBinding;
import com.example.androidtestapp.model.Result;
import com.example.androidtestapp.utils.CommonMethods;
import com.example.androidtestapp.utils.Constants;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private CommonMethods commonMethods;
    private Result resultItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        init();
        if (resultItem != null) setUI();
    }

    private void init() {
        commonMethods = new CommonMethods();
        resultItem = getIntent().getParcelableExtra(Constants.RESULT);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUI() {
        binding.setItem(resultItem);
        binding.tvCreatedAt.setText(getString(R.string.created_at_with_colon, commonMethods.formatDate(resultItem.getCreatedAt())));
        Glide.with(this).load(Objects.requireNonNull(resultItem.getImageUrls()).get(0)).into(binding.ivItem);
        binding.progressbar.setVisibility(View.GONE);
    }
}