package gr.aueb.cf.qrgallery.DAO;

public interface IArtworkDAO {
    /**
     * Retrieves artwork data from the provided URL.
     *
     * @param url The URL from which to retrieve the artwork data.
     */
    void retrieveArtworkData(String url);
}

