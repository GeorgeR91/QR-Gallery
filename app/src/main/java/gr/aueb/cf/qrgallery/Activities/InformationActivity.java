package gr.aueb.cf.qrgallery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import gr.aueb.cf.qrgallery.R;

public class InformationActivity extends AppCompatActivity {
    private ImageView imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Initialize the ImageView and set a click listener on it
        imageView8 = findViewById(R.id.imageView8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MainActivity when the ImageView is clicked
                Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}