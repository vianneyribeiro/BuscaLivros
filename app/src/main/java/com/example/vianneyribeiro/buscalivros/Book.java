package com.example.vianneyribeiro.buscalivros;

/**
 * Created by vianneyribeiro on 16/01/2018.
 */


public class Book {

    private String title;
    private String author;

    /**
     * Constructs {@link Book}.
     *
     * @param title
     * @param author
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Returns title of the book.
     */
    public String getTitle() {

        return this.title;
    }

    /**
     * Returns authors of the book.
     */
    public String getAuthor() {

        return this.author;
    }

}
