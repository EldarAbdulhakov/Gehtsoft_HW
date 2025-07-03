package abdulhakov.eldar.LombokAnnotationsPractice;

import lombok.SneakyThrows;

public class Utils {

    // Аннотация @SneakyThrows из библиотеки Lombok позволяет не объявлять проверяемые исключения (checked exceptions),
    // которые в обычном Java-коде требуют try-catch или throws.

    @SneakyThrows
    public static void sleep(int millis) {
        Thread.sleep(millis);
    }

    public static void main(String[] args) {
        sleep(3000);
    }
}
