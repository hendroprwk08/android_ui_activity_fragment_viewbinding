package com.hendro.halodunia;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.hendro.halodunia.databinding.ActivityFragmentBinding;
import java.util.Objects;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFragmentBinding binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.topAppBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gantiFragment(R.id.tempat_fragment, new SatuFragment());

        binding.btFrSatu.setOnClickListener(view -> gantiFragment(R.id.tempat_fragment, new SatuFragment()));

        binding.btFrDua.setOnClickListener(view -> gantiFragment(R.id.tempat_fragment, new DuaFragment()));

        binding.btFrTiga.setOnClickListener(view -> gantiFragment(R.id.tempat_fragment, new TigaFragment()));
    }

    private void gantiFragment(int containerId, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
    }
}