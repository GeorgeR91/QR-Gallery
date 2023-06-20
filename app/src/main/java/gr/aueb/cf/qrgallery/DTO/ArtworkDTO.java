package gr.aueb.cf.qrgallery.DTO;

public class ArtworkDTO {

    private String Title;
    private String Artist;
    private String Year;
    private String Medium;
    private String Movement;
    private String Dimensions;

    // Default constructor
    public ArtworkDTO() {
    }

    /**
     * Constructor to initialize the ArtworkDTO object with provided details.
     *
     * @param title      The title of the artwork.
     * @param artist     The artist of the artwork.
     * @param year       The year of creation of the artwork.
     * @param medium     The medium of the artwork.
     * @param movement   The movement/style of the artwork.
     * @param dimensions The dimensions of the artwork.
     */
    public ArtworkDTO(String title, String artist, String year, String medium, String movement, String dimensions) {
        Title = title;
        Artist = artist;
        Year = year;
        Medium = medium;
        Movement = movement;
        Dimensions = dimensions;
    }

    public String getTitle() {
        return Title;
    }

    public String getArtist() {
        return Artist;
    }

    public String getYear() {
        return Year;
    }

    public String getMedium() {
        return Medium;
    }

    public String getMovement() {
        return Movement;
    }

    public String getDimensions() {
        return Dimensions;
    }
}
