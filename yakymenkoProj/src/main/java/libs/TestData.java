package libs;

/**
 * Клас, дле ми будемо зберігати ті тестові дані де вони будуть використовуватись у багатьох місцях
 */
public class TestData {
    public final static String VALID_LOGIN = "qaauto"; // якщо final(константа -  незмінна) → CAPS_LOCK
    public final static String VALID_PASSWORD = "123456qwerty";// static - доступ до неї можемо отримати через ім'я класу(TestData) і змінної(VALID_PASSWORD) в будь-якому місці програми (тому що створюється спочатку і займає місце поки наша програма виконується)
}
