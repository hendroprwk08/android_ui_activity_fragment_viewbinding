package com.hendro.halodunia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.hendro.halodunia.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ActivityResultLauncher<Intent> intentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    assert result.getData() != null;
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
        binding.topAppBar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.i_pesan) {
                startActivity(new Intent(getApplicationContext(), PesanActivity.class));
                return true;
            } else if (id == R.id.i_fragment) {
                startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
                return true;
            } else {
                return false;
            }
        });

        binding.btProfil.setOnClickListener(view -> startActivity(new Intent(view.getContext(), ProfileActivity.class)));

        binding.btSimpan.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("NAMA", binding.etNama.getText().toString());
            intent.putExtra("USIA", binding.etUsia.getText().toString());
            startActivity(intent);
        });

        binding.btUlangi.setOnClickListener(view -> {
            binding.etNama.setText(null);
            binding.etUsia.setText(null);
        });

        binding.btHitung.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), HitungActivity.class);
            intentLauncher.launch(intent);
        });
    }
}