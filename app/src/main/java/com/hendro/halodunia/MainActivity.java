package com.hendro.halodunia;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.hendro.halodunia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    binding.etHasil.setText(result.getData().getStringExtra("nilai_hasil"));
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ProfileActivity.class));
            }
        });

        binding.btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("NAMA", binding.etNama.getText().toString());
                intent.putExtra("USIA", binding.etUsia.getText().toString());
                startActivity(intent);
            }
        });

        binding.btUlangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etNama.setText(null);
                binding.etUsia.setText(null);
            }
        });

        binding.btHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),HitungActivity.class);
                intentLauncher.launch(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.i_pesan:
                startActivity(new Intent(getApplicationContext(), PesanActivity.class));
                return true;
            case R.id.i_fragment:
                startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}