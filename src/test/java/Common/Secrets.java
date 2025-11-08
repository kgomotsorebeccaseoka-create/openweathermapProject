package Common;

public class Secrets {
    private Secrets() {}
    public static String openWeatherKey() {
        String key = System.getenv("OPENWEATHER_API_KEY");
        if (key == null || key.isBlank()) {
            key = System.getProperty("OPENWEATHER_API_KEY");
        }
        if (key == null || key.isBlank()) {
            throw new IllegalStateException(
                    "Missing API key: set environment variable `OPENWEATHER_API_KEY` or run tests with `-DOPENWEATHER_API_KEY=your_key`"
            );
        }
        return key;
    }
}
