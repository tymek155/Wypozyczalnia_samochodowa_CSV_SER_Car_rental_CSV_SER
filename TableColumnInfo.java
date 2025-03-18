import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableColumnInfo {
    String name(); // Nazwa kolumny
    int order(); // Kolejność kolumny
}

