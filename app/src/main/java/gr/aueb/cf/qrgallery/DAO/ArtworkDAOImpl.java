package gr.aueb.cf.qrgallery.DAO;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import gr.aueb.cf.qrgallery.Activities.ScanActivity;
import gr.aueb.cf.qrgallery.DTO.ArtworkDTO;
import gr.aueb.cf.qrgallery.Helpers.ArtworkDisplayHelper;

public class ArtworkDAOImpl implements IArtworkDAO {
    private ScanActivity scanActivity;

    public ArtworkDAOImpl(ScanActivity scanActivity) {
        this.scanActivity = scanActivity;
    }

    @Override
    public void retrieveArtworkData(String url) {
        // Create a RequestQueue using the scanActivity
        RequestQueue requestQueue = Volley.newRequestQueue(scanActivity);

        // Create a StringRequest to retrieve data from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse the response as a JSON object
                            JSONObject jsonObject = new JSONObject(response);
                            // Extract the necessary information from the JSON object
                            String title = jsonObject.getString("Title");
                            String artist = jsonObject.getString("Artist");
                            String year = jsonObject.getString("Year");
                            String medium = jsonObject.getString("Medium");
                            String movement = jsonObject.getString("Movement");
                            String dimensions = jsonObject.getString("Dimensions");

                            // Create an ArtworkDTO object with the extracted information
                            ArtworkDTO artwork = new ArtworkDTO(title, artist, year, medium, movement, dimensions);

                            // Display the artwork data using the ArtworkDisplayHelper
                            ArtworkDisplayHelper.displayArtworkData(scanActivity, scanActivity.getScrollTV(), artwork);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error by displaying an error message
                            scanActivity.displayErrorMessage();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Display an error message when there's an error retrieving artwork data
                        Toast.makeText(scanActivity, "Error retrieving artwork data", Toast.LENGTH_SHORT).show();
                        scanActivity.displayErrorMessage();
                    }
                });

        // Add the stringRequest to the requestQueue
        requestQueue.add(stringRequest);
    }
}
