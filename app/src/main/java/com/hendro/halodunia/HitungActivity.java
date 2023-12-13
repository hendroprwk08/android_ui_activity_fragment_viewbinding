package com.hendro.halodunia;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.hendro.halodunia.databinding.ActivityHitungBinding;

public class HitungActivity extends AppCompatActivity {

    private ActivityHitungBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHitungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.btTambah.setOnClickListener(view -> {
            int nilai_1 = Integer.parseInt(binding.etNilai1.getText().toString());
            int nilai_2 = Integer.parseInt(binding.etNilai2.getText().toString());
            int hasil = nilai_1 + nilai_2;

            binding.etHasil.setText(String.valueOf(hasil));
        });

        binding.btKurang.setOnClickListener(view -> {
            int nilai_1 = Integer.parseInt(binding.etNilai1.getText().toString());
            int nilai_2 = Integer.parseInt(binding.etNilai2.getText().toString());
            int hasil = nilai_1 - nilai_2;

            binding.etHasil.setText(String.valueOf(hasil));
        });

        binding.btKali.setOnClickListener(view -> {
            int nilai_1 = Integer.parseInt(binding.etNilai1.getText().toString());
            int nilai_2 = Integer.parseInt(binding.etNilai2.getText().toString());
            int hasil = nilai_1 * nilai_2;

            binding.etHasil.setText(String.valueOf(hasil));
        });

        binding.btBagi.setOnClickListener(view -> {
            int nilai_1 = Integer.parseInt(binding.etNilai1.getText().toString());
            int nilai_2 = Integer.parseInt(binding.etNilai2.getText().toString());
            float hasil = (float) nilai_1 / nilai_2;

            binding.etHasil.setText(String.valueOf(hasil));
        });

        binding.btSelesai.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("nilai_hasil", binding.etHasil.getText().toString());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}