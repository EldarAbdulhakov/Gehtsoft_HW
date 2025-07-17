package hw02.LombokAnnotationsPractice;

import lombok.AllArgsConstructor;

// Создаёт конструктор со всеми полями класса в порядке их объявления.

@AllArgsConstructor
public class Book {

    private String title;
    private String author;
    private int pages;
    private double price;

    public static void main(String[] args) {
     Book book = new Book("tit", "aut", 350, 112.9);
    }
}
