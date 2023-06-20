package gr.aueb.cf.qrgallery.Helpers;

import android.widget.TextView;

import gr.aueb.cf.qrgallery.Activities.ScanActivity;
import gr.aueb.cf.qrgallery.DTO.ArtworkDTO;

public class ArtworkDisplayHelper {

    /**
     * Displays the artwork data in the provided TextView.
     *
     * @param context  The ScanActivity context.
     * @param textView The TextView to display the artwork data.
     * @param artwork  The ArtworkDTO object containing the artwork details.
     */
    public static void displayArtworkData(ScanActivity context, TextView textView, ArtworkDTO artwork) {
        // Construct the artwork details string
        String artworkDetails = "Title: " + artwork.getTitle() +
                "\nArtist: " + artwork.getArtist() +
                "\nYear: " + artwork.getYear() +
                "\nMedium: " + artwork.getMedium() +
                "\nMovement: " + artwork.getMovement() +
                "\nDimensions: " + artwork.getDimensions();

        // Set the artwork details string to the textView
        textView.setText(artworkDetails);
    }
}
