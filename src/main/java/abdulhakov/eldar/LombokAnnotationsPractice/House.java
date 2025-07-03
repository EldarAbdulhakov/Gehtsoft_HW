package abdulhakov.eldar.LombokAnnotationsPractice;

import lombok.Builder;

// Аннотация @Builder из Lombok — это удобный способ создать объекты с помощью паттерна Строитель (Builder),
// чтобы создавать экземпляры класса по шагам, особенно если у класса много полей.
// Генерирует вложенный статический класс Builder с методами для установки каждого поля.
// Позволяет создавать объект с цепочкой вызовов (fluent API).

@Builder
public class House {

    private String address;
    private int rooms;
    private double area;
    private double price;

    public static void main(String[] args) {
        House house = House.builder()
                .address("dfgdfg")
                .rooms(4)
                .area(70.5)
                .price(70005.5)
                .build();
    }
}
