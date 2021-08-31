package com.hendro.halodunia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.hendro.halodunia.databinding.ActivityFragmentBinding;
import com.hendro.halodunia.databinding.ActivityMainBinding;

public class FragmentActivity extends AppCompatActivity {

    private ActivityFragmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gantiFragment(R.id.tempat_fragment, new SatuFragment());

        binding.btFrSatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gantiFragment(R.id.tempat_fragment, new SatuFragment());
            }
        });

        binding.btFrDua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gantiFragment(R.id.tempat_fragment, new DuaFragment());
            }
        });

        binding.btFrTiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gantiFragment(R.id.tempat_fragment, new TigaFragment());
            }
        });
    }

    private void gantiFragment(int containerId, Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
    }
}