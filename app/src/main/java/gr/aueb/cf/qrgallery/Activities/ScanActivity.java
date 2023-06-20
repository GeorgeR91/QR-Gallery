package gr.aueb.cf.qrgallery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import gr.aueb.cf.qrgallery.DAO.ArtworkDAOImpl;
import gr.aueb.cf.qrgallery.DAO.IArtworkDAO;
import gr.aueb.cf.qrgallery.DTO.ArtworkDTO;
import gr.aueb.cf.qrgallery.R;

public class ScanActivity extends AppCompatActivity {
    private TextView scrollTV;
    private ImageView imageView9;
    private ArrayList<ArtworkDTO> artworkArrayList;
    private IArtworkDAO artworkDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Initialize UI elements
        scrollTV = findViewById(R.id.scrollTV);
        imageView9 = findViewById(R.id.imageView9);

        // Initialize data structures
        artworkArrayList = new ArrayList<>();
        artworkDAO = new ArtworkDAOImpl(this);

        // Set click listener on image view
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the scanner when the image view is clicked
                startScanner();
            }
        });

        // Start the scanner initially
        startScanner();
    }

    // Start the QR code scanner
    private void startScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);

        // Set scanner properties
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR Code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);

        // Initiate the scanning process
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // If scanning was cancelled, display a toast and navigate back to MainActivity
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
                navigateToMainActivity();
            } else {
                // Retrieve the artwork data based on the scanned QR code contents
                String url = "https://qrgallery-74cba-default-rtdb.europe-west1.firebasedatabase.app/artworks/" + result.getContents() + ".json";
                artworkDAO.retrieveArtworkData(url);
            }
        }
    }

    // Getter for the scroll text view
    public TextView getScrollTV() {
        return scrollTV;
    }

    // Display an error message on the scroll text view
    public void displayErrorMessage() {
        String errorMessage = "Invalid QR Code.\nPlease press the button below to\nscan again.";
        scrollTV.setText(errorMessage);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Navigate back to MainActivity when back button is pressed
        navigateToMainActivity();
    }

    // Navigate to MainActivity and clear the activity stack
    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}