package abdulhakov.eldar.LombokAnnotationsPractice;

import lombok.Setter;
import lombok.experimental.Accessors;

// Аннотация @Accessors(chain = true) от Lombok позволяет включить fluent API (цепочный API, текучий интерфейс),
// когда сеттеры возвращают this, и можно вызывать эти методы цепочкой (chain).

@Setter
@Accessors(chain = true)
public class Configuration {

    private String url;
    private String  user;
    private String  password;
    private int size;
    private boolean flag;

    Configuration configuration = new Configuration()
            .setUrl("https://sdfs.com")
            .setUser("admin")
            .setPassword("****")
            .setSize(123)
            .setFlag(true);
}
