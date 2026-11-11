package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  implements Fragment2.OnFragmentInteractionListener, Fragment3.OnCheckboxChangeListener {

    private ActivityMainBinding binding;

    private int currentFragment = 1;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        Fragment2 fragment2 = Fragment2.newInstance(null, null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {
            if (currentFragment == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                currentFragment = 2;
                binding.buttonContinue.setVisibility(View.GONE);
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                currentFragment = 1;
                binding.buttonContinue.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public void onContinueClicked(String name) {
        this.name = name;
        Fragment3 fragment3 = Fragment3.newInstance(name);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();
        currentFragment = 3;
    }

    @Override
    public void onCheckboxChanged(boolean isChecked) {
        Fragment3.fragment3Binding.buttonFinish.setEnabled(isChecked);
        if (isChecked) {
            Fragment3.fragment3Binding.buttonFinish.setText(R.string.finish_text);
        } else {
            Fragment3.fragment3Binding.buttonFinish.setText(R.string.continue_text);
        }
    }
}
