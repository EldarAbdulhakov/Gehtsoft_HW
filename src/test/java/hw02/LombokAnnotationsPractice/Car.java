package hw02.LombokAnnotationsPractice;

import lombok.RequiredArgsConstructor;

// Аннотация @RequiredArgsConstructor из библиотеки Lombok автоматически генерирует конструктор
// с параметрами для всех final и @NonNull полей. Поля без final и без @NonNull в конструктор не включаются.

@RequiredArgsConstructor
public class Car {

    private final String brand;
    private final String model;
    private String noFinal;

    Car car = new Car("Volvo", "940");
}
