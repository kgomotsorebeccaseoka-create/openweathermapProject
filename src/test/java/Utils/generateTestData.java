package Utils;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.UUID;

public class generateTestData {

    static Faker faker = new Faker();

    public static String externalId = UUID.randomUUID().toString();
    public static String firstName=faker.name().firstName();
    public static Float latitude = Float.valueOf(faker.address().latitude());
    public static Float longitude = Float.valueOf(faker.address().longitude());
    public static Float altitude = Float.valueOf(faker.number().numberBetween(0, 3000));
    public static String name = firstName + "'s Station";
}
