package org.kimp.mu.step;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.kimp.mu.step.cache.AppCache;
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

    @Override
    protected void onResume() {
        super.onResume();

        AppCache.getInstance().setImageFromFirebaseWithCache("test/cache_cat.jpg", "testCat", binding.testImageView);
    }
}