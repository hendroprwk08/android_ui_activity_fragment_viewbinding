package com.hendro.halodunia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hendro.halodunia.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        //tangkap nilai dari Intent
        Intent intent = getIntent();
        binding.etNama.setText( intent.getStringExtra("NAMA") );
        binding.etUsia.setText( intent.getStringExtra("USIA") );
    }
}