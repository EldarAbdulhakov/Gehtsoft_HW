package abdulhakov.eldar.LombokAnnotationsPractice;

// Аннотации @Setter и @Getter из библиотеки Lombok автоматически генерируют сеттеры и геттеры для полей класса.
// Можно повесить аннотацию на уровень поля — генерация для конкретного поля.
// Можно повесить аннотацию на класс — генерация для всех нестатических полей.

import lombok.Getter;
import lombok.Setter;

public class Product {

    @Setter
    @Getter
    private String name;

    @Setter
    private double price;

    @Getter
    private final String expirationDate = "10.10.2030";

    public static void main(String[] args) {
        Product product = new Product();

        product.setName("Mike");
        System.out.println(product.getName());

        product.setPrice(10.5);

        System.out.println(product.getExpirationDate());
    }
}
