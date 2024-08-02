package tech.java.workflowejbs.projections;

public interface BookProjection {
    String getIsbn();
    String getTitle();
    String getAuthor();
    int getPublicationYear();
    int getavailableCopies();
    String getGenre();
}
