package org.kimp.mu.step;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import org.kimp.mu.step.databinding.ActivityUniversitiesBinding;

public class UniversitiesActivity extends AppCompatActivity {

    private ActivityUniversitiesBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUniversitiesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.uaBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}