package libs;

public enum Color {
    BLUE ("rgb(128, 189, 255)");

    final String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
