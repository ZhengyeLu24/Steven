import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Movie {
    // Unique movie ID, e.g., "M001"
    private String id;
    // Movie title
    private String title;
    // Genre, e.g., "Drama", "Action"
    private String type;
    // Release year
    private int year;
    // Rating (0.0 ~ 10.0)
    private double rating;

    // No-arg constructor (convenient for beginners and future extension)
    public Movie() {}

    // Parameterized constructor
    public Movie(String id, String title, String type, int year, double rating) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.year = year;
        this.rating = rating;
    }

    // Basic getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    /**
     * Parse a Movie object from a CSV line.
     */
    public static Movie fromCsvRow(String row) {
        if (row == null) {
            throw new IllegalArgumentException("CSV row is empty");
        }
        // Split into 5 columns by comma
        String[] parts = row.split(",");
        // Simple format check: must be exactly 5 columns
        if (parts.length != 5) {
            throw new IllegalArgumentException("CSV format error, requires 5 columns");
        }
        // Read columns sequentially and trim whitespace
        String id = parts[0].trim();
        String title = parts[1].trim();
        String type = parts[2].trim();
        int year = Integer.parseInt(parts[3].trim()); // 4th column: Year (parsed as integer)
        double rating = Double.parseDouble(parts[4].trim()); // 5th column: Rating (parsed as double)
        String g = type.toLowerCase();
        if (g.equals("biography") || g.equals("documentary")) {
            return new Documentary(id, title, type, year, rating);
        }
        return new FeatureFilm(id, title, type, year, rating);
    }

    /**
     * Batch load movie list from CSV file. Automatically skip header if it starts with "id,".
     */
    public static ArrayList<Movie> loadFromCsv(String csvPath) {
        ArrayList<Movie> list = new ArrayList<Movie>(); // Store all loaded movies
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvPath)); // Open file and read line by line
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Trim whitespace from both ends
                if (line.isEmpty()) continue; // Skip empty lines
                if (first) {
                    first = false;
                    if (line.toLowerCase().startsWith("id,")) {
                        continue; // Skip header
                    }
                }
                try {
                    Movie m = Movie.fromCsvRow(line); // Parse a line to generate Movie object
                    list.add(m);
                } catch (Exception e) {
                    System.err.println("Parse failed, skipping: " + line); // Invalid data, print info and skip
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        } finally {
            if (br != null) {
                try { br.close(); } catch (IOException ignored) {} // Close file
            }
        }
        return list;
    }

    public static ArrayList<Movie> loadFromCsv() {
        return loadFromCsv("data/movies.csv");
    }
    @Override
    public String toString() {
        return id + " - " + title + " (" + type + ", " + year + ", " + rating + ")";
    }
}
