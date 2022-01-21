package org.kimp.mu.step;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import org.kimp.mu.step.databinding.ActivityMainBinding;
import org.kimp.mu.step.fragments.MainMenuFragment;

public class MainActivity extends AppCompatActivity implements FragmentResultListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                            .setReorderingAllowed(true)
                            .add(binding.maMenuFragment.getId(), MainMenuFragment.class, null)
                            .commit();
                }
            }, 600);
        }
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if (result.getString("action").equals("start")) {
            // TODO
        }
        if (result.getString("action").equals("settings")) {
            // TODO
        }
    }
}