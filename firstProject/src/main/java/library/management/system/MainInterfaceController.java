package library.management.system;

import main.classes.BorrowData;
import main.classes.Borrower;
import main.classes.Document;
import main.classes.LibraryManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.HashMap;

public class MainInterfaceController {
    private JPanel jpnView;
    private Borrower borrower;
    private LibraryManagementSystem libraryManagementSystem;
    private JPanel topBorrowedBooksjpn;
    private JPanel topSuitableBooksjpn;

    MainInterfaceController(JPanel jpnView, Borrower borrower, LibraryManagementSystem libraryManagementSystem, JPanel topBorrowedBooksjpn, JPanel topSuitableBooksjpn) {
        this.jpnView = jpnView;
        this.borrower = borrower;
        this.libraryManagementSystem = libraryManagementSystem;
        this.topBorrowedBooksjpn = topBorrowedBooksjpn;
        this.topSuitableBooksjpn = topSuitableBooksjpn;

        displayBooks(topBorrowedBooksjpn, "best books");
        displayBooks(topSuitableBooksjpn, "most suitable books");
    }

    private void displayBooks(JPanel jpn, String kind) {
        Vector<Document> books = null;

        if (kind.equals("best books")) {
            books = FiveBestBooks();
        } else if (kind.equals("most suitable books")) {
            books = FiveMostSuitableBooks();
        }

        jpn.removeAll();
        jpn.setLayout(new FlowLayout(FlowLayout.LEFT));  // Layout for arranging books horizontally

        // Create an ExecutorService to manage multiple threads efficiently, using a maximum of 4 threads.
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Document book : books) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));  // Layout for stacking cover and button vertically

            // Submit the task of loading the book cover to the ExecutorService
            executor.submit(() -> {
                try {
                    // Load the book cover image from the provided URL or path
                    ImageIcon bookCover = new ImageIcon(book.getDocumentImageUrl());

                    // Update the UI with the loaded image on the Event Dispatch Thread (EDT)
                    SwingUtilities.invokeLater(() -> {
                        DisplayOrUpdateDocumentController.addBookCoverToJPanel(book.getDocumentImageUrl(), bookPanel, 110, 150);
                    });
                } catch (Exception e) {
                    e.printStackTrace();  // Handle any exceptions that may occur during image loading
                }
            });

            // Create the "BORROW" button and set its preferred size
            JButton detailsButton = new JButton("|_BORROW_|");
            detailsButton.setPreferredSize(new Dimension(120, 30));

            // Add an action listener to show book details when the button is clicked
            detailsButton.addActionListener(e -> showBookDetails(book));
            bookPanel.add(detailsButton);  // Add the button below the cover image

            // Add the book panel (which includes the cover and button) to the main panel
            jpn.add(bookPanel);
        }

        // Once done, shutdown the ExecutorService to release resources
        executor.shutdown();

        jpn.revalidate();
        jpn.repaint();
    }

    private void showBookDetails(Document book) {
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());

        jpnView.add(new BorrowOrRemoveDocumentJPanel(borrower, libraryManagementSystem, book.getDocumentISBN()));

        jpnView.revalidate();
        jpnView.repaint();
    }

    /**
     * Retrieves the top 5 books with the highest average ratings from the library.
     * The books are sorted in descending order of their average ratings.
     * If fewer than 5 books have ratings, returns all rated books.
     *
     * @return A {@code Vector<Document>} containing the top 5 highest-rated books.
     *         If no books have ratings, the vector will be empty.
     */
    private Vector<Document> FiveBestBooks() {
        // Create a stream from the book list
        return libraryManagementSystem.getAllDocuments()
                .stream()
                .filter(book -> book.getDocumentRatingsCount() > 0) // Include only books with ratings
                .sorted((book1, book2) -> Double.compare(book2.getDocumentAverageRating(), book1.getDocumentAverageRating())) // Sort by average rating (descending)
                .limit(5) // Limit to top 5 books
                .collect(Collectors.toCollection(Vector::new)); // Collect into a Vector
    }

    /**
     * Creates a utility matrix for collaborative filtering.
     * Rows represent borrowers, columns represent documents (books),
     * and the values represent the ratings given by borrowers to documents.
     *
     * @return A Map representing the utility matrix where the value is the rating, or null if not rated.
     */
    private Map<Borrower, Map<Document, Integer>> createUtilityMatrix() {
        // Get all borrowers and documents
        Vector<Borrower> borrowers = libraryManagementSystem.borrowerList;
        Vector<Document> documents = libraryManagementSystem.getAllDocuments();

        Map<Borrower, Map<Document, Integer>> utilityMatrix = new HashMap<>();

        // Iterate over each borrower to create their ratings row
        for (Borrower borrower : borrowers) {
            Map<Document, Integer> borrowerRatings = new HashMap<>();

            // Iterate over each document to check the borrower's rating
            for (Document document : documents) {
                // Check if the borrower has borrowed this document
                BorrowData borrowData = borrower.borrowedHistory.stream()
                        .filter(b -> b.getBorrowedBookISBN().equals(document.getDocumentISBN()))
                        .findFirst()
                        .orElse(null);

                if (borrowData != null) {
                    // If the borrower has rated this document, add the rating
                    int rating = borrowData.getUserRating();
                    borrowerRatings.put(document, rating);  // Rating 0 for "no rating"
                } else {
                    // If the borrower has not rated this document, mark it with 0
                    borrowerRatings.put(document, 0);  // No rating
                }
            }

            // Add the ratings map for the borrower to the utility matrix
            utilityMatrix.put(borrower, borrowerRatings);
        }

        return utilityMatrix;
    }

    /**
     * Normalizes the utility matrix by adjusting each borrower's ratings based on their average rating.
     * This helps to remove any bias from users who tend to rate items higher or lower than others.
     * The normalized ratings are calculated by subtracting each borrower's average rating from their individual ratings.
     * Missing ratings (represented by 0) are left unchanged in the normalized matrix.
     *
     * @param utilityMatrix A map representing the utility matrix, where each borrower is mapped to their ratings for various documents.
     *                      The map's structure is {@code Map<Borrower, Map<Document, Integer>>}, with the rating as an integer value.
     *                      A value of 0 represents a missing rating.
     * @return A map representing the normalized utility matrix, where the ratings have been adjusted by subtracting the borrower's average rating.
     *         The map's structure is the same as the input, but the ratings are normalized.
     */
    private Map<Borrower, Map<Document, Double>> normalizeUtilityMatrix(Map<Borrower, Map<Document, Integer>> utilityMatrix) {
        Map<Borrower, Map<Document, Double>> normalizedMatrix = new HashMap<>();

        // Iterate through each borrower in the utility matrix
        for (Map.Entry<Borrower, Map<Document, Integer>> borrowerEntry : utilityMatrix.entrySet()) {
            Borrower borrower = borrowerEntry.getKey();
            Map<Document, Integer> ratings = borrowerEntry.getValue();

            // Calculate the average rating of the borrower
            double totalRatings = 0.0;
            int ratingsCount = 0;

            for (Integer rating : ratings.values()) {
                if (rating != 0) {  // Only consider non-zero ratings
                    totalRatings += rating;
                    ratingsCount++;
                }
            }

            // Calculate the average rating, if there are any ratings
            double averageRating = 0.0;
            if (ratingsCount > 0) {
                averageRating = totalRatings / ratingsCount;
            }

            // Create a map for normalized ratings (using Double for precision)
            Map<Document, Double> normalizedRatings = new HashMap<>();

            // Normalize the ratings by subtracting the average rating from each rating
            for (Map.Entry<Document, Integer> entry : ratings.entrySet()) {
                Document document = entry.getKey();
                int rating = entry.getValue();

                // Normalize the rating (subtract the average rating)
                if (rating != 0) {
                    normalizedRatings.put(document, (double) rating - averageRating);
                } else {
                    normalizedRatings.put(document, 0.0);  // No rating, assign 0.0
                }
            }

            // Put the normalized ratings for the borrower in the normalizedMatrix
            normalizedMatrix.put(borrower, normalizedRatings);
        }

        return normalizedMatrix;
    }

    /**
     * Computes the cosine similarity between two borrowers based on their ratings for common documents.
     * Cosine similarity is calculated using the formula:
     *     cosine_similarity(u1, u2) = (u1^T * u2) / (||u1|| * ||u2||)
     * @param borrowerRatings A map of documents to ratings for the first borrower.
     * @param otherRatings A map of documents to ratings for the second borrower.
     * @return The cosine similarity score between the two borrowers. A value between -1 and 1, where 1 means identical ratings and -1 means opposite ratings.
     */
    private double cosineSimilarity(Map<Document, Double> borrowerRatings, Map<Document, Double> otherRatings) {
        // Initialize dot product and norms
        double dotProduct = 0.0;
        double borrowerNorm = 0.0;
        double otherNorm = 0.0;

        // Iterate over all documents to calculate dot product and norms
        for (Map.Entry<Document, Double> entry : borrowerRatings.entrySet()) {
            Document document = entry.getKey();
            Double borrowerRating = entry.getValue();
            Double otherRating = otherRatings.get(document);  // Corresponding rating from the other borrower

            if (otherRating != null) {  // Only consider documents that both borrowers rated
                dotProduct += borrowerRating * otherRating;
                borrowerNorm += borrowerRating * borrowerRating;
                otherNorm += otherRating * otherRating;
            }
        }

        // If both norms are zero, return similarity of 0 (no common ratings)
        if (borrowerNorm == 0.0 || otherNorm == 0.0) {
            return 0.0;
        }

        // Calculate cosine similarity
        return dotProduct / (Math.sqrt(borrowerNorm) * Math.sqrt(otherNorm));
    }

    /**
     * Computes the cosine similarity between the specified borrower and all other borrowers.
     * This method uses the normalized ratings from the utility matrix to calculate the similarity
     * between the target borrower and other borrowers based on their ratings for shared documents.
     *
     * @param normalizedUtilityMatrix A map containing each borrower's normalized ratings for documents.
     * @return A map where the keys are other borrowers and the values are the similarity scores
     *         between the specified borrower and those borrowers.
     */
    private Map<Borrower, Double> computeSimilarities(Map<Borrower, Map<Document, Double>> normalizedUtilityMatrix) {
        Map<Borrower, Double> similarities = new HashMap<>();

        //The ratings of the borrower using this system now
        Map<Document, Double> specifiedRatings = normalizedUtilityMatrix.get(borrower);
        if (specifiedRatings == null) {
            return new HashMap<>();
        }

        // Iterate over all borrowers in the normalized utility matrix
        for (Map.Entry<Borrower, Map<Document, Double>> entry : normalizedUtilityMatrix.entrySet()) {
            Borrower otherBorrower = entry.getKey();
            if (!this.borrower.equals(otherBorrower)) {  // Avoid comparing the borrower to themselves
                Map<Document, Double> otherRatings = entry.getValue();
                double similarity = cosineSimilarity(specifiedRatings, otherRatings);  // Compute similarity
                similarities.put(otherBorrower, similarity);
            }
        }

        return similarities;
    }

    /**
     * Proposes a list of documents to a borrower based on their similarity to other borrowers.
     * The proposed documents are weighted based on the similarity between the borrowers and the ratings
     * given by other borrowers for those documents.
     * <p>
     * For each borrower that is similar to the current borrower, the method checks the documents they have rated
     * but the current borrower has not yet rated. It then calculates a weighted score for each such document
     * based on the similarity score and the rating by the similar borrower.
     * The final proposed documents list will contain documents that have the highest weighted scores.
     * </p>
     *
     * @param similarities A map containing the similarity scores between the current borrower and all other borrowers.
     *                     The key is the other borrower, and the value is the similarity score between them.
     * @param utilityMatrix A map representing the ratings of all borrowers for all documents.
     *                      The key is a borrower, and the value is another map where the key is a document and the value is the rating for that document.
     * @return A {@link Vector} of recommended documents sorted by their weighted scores.
     *         Each document is recommended based on its weighted score, which is calculated from the similarity scores and ratings.
     */
    private Vector<Document> proposeDocuments(Map<Borrower, Double> similarities, Map<Borrower, Map<Document, Integer>> utilityMatrix) {
        Map<Document, Double> proposedDocuments = new HashMap<>();

        // Sort similarities to get the top N most similar borrowers
        List<Map.Entry<Borrower, Double>> sortedSimilarities = similarities.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)  // Only include borrowers with positive similarity
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Sort by descending similarity
                .collect(Collectors.toList()); // Collect into a list

        // Iterate through the similar borrowers
        for (Map.Entry<Borrower, Double> similarityEntry : sortedSimilarities) {
            Borrower similarBorrower = similarityEntry.getKey(); // The borrower who is similar to this borrower
            double similarityScore = similarityEntry.getValue();

            // Get the ratings for the similar borrower from the original (non-normalized) utility matrix
            Map<Document, Integer> similarBorrowerRatings = utilityMatrix.get(similarBorrower);

            // For each document the similar borrower has rated, if the current borrower hasn't rated it, consider it
            for (Map.Entry<Document, Integer> ratingEntry : similarBorrowerRatings.entrySet()) {
                Document document = ratingEntry.getKey();
                Integer rating = ratingEntry.getValue();

                if (utilityMatrix.get(borrower).get(document) == 0) {  // Check if the document has not been rated by the current borrower
                    // Calculate a weighted score based on similarity score and the document rating
                    double weightedScore = similarityScore * rating;
                    proposedDocuments.put(document, weightedScore);
                }
            }
        }

        // Sort the proposed documents by the highest weighted score
        return proposedDocuments.entrySet()
                .stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Sort by score
                .map(Map.Entry::getKey)
                .limit(5) // Get the top 5 most suitable documents
                .collect(Collectors.toCollection(Vector::new)); // Return as Vector
    }

    /**
     * Retrieves the five most suitable books for a borrower based on collaborative filtering.
     * <p>
     * The method performs the following steps:
     * 1. Creates a utility matrix where each borrower's ratings for documents are recorded.
     * 2. Normalizes the ratings in the utility matrix to account for differences in borrower rating tendencies.
     * 3. Computes cosine similarity scores between the current borrower and all other borrowers.
     * 4. Proposes the most suitable documents based on the similarity scores and ratings of similar borrowers.
     * </p>
     *
     * @return A {@link Vector} of the top 5 most suitable books (documents) for the borrower,
     *         recommended based on collaborative filtering and similarity to other borrowers.
     */
    private Vector<Document> FiveMostSuitableBooks() {
        //Step 1: Create a utility matrix
        Map<Borrower, Map<Document, Integer>> utilityMatrix = createUtilityMatrix();

        //Step 2: Normalize the utility matrix
        Map<Borrower, Map<Document, Double>> normalizedUtilityMatrix = normalizeUtilityMatrix(utilityMatrix);

        //Step 3: Compute the cosine similarities
        Map<Borrower, Double> similarities = computeSimilarities(normalizedUtilityMatrix);

        //Step 4: Propose suitable documents
        return proposeDocuments(similarities, utilityMatrix);
    }
}
