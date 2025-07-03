package abdulhakov.eldar.LombokAnnotationsPractice;

import lombok.Data;

// Аннотация @Data в Lombok — это комплексная аннотация, которая автоматически генерирует
// геттеры для всех полей,
// сеттеры для всех не-final полей,
// конструктор для всех final/@NonNull полей,
// equals(), hashCode(), toString()

@Data
public class Person {

    private final String name;
    private int age;
    private String email;

    public static void main(String[] args) {
        Person person = new Person("name");

        System.out.println(person.getName());

        person.setAge(25);
        person.setEmail("sdf@sdf.com");
        System.out.println(person.getAge());
        System.out.println(person.getEmail());

        System.out.println(person.toString());
        System.out.println(person.age);

        System.out.println(person.getName().equals("name"));

        Person person2 = person;
        System.out.println(person.hashCode() + " " + person2.hashCode());
    }
}
