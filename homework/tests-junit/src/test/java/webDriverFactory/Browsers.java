package webDriverFactory;

public enum Browsers {
    CHROME("CHROME"),
    SAFARI("SAFARI"),
    OPERA("OPERA"),
    FIREFOX("FIREFOX");

    private final String browserName;

    Browsers(String browserName) {
        this.browserName = browserName;
    }

    public static Browsers getBrowserByStringName(String browserName) {
        for (Browsers br : Browsers.values()) {
            if (br.browserName.equals(browserName.toUpperCase())) {
                return br;
            }

        }
        throw new IllegalArgumentException("Такой браузер отсутствует");
    }
}
