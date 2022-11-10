package libs;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:./src/main/java/resources/config.properties")
public interface ConfigProperties extends Config {
	long TIME_FOR_DEFAULT_WAIT();
	long TIME_FOR_EXPLICIT_WAIT_LOW();
	long TIME_FOR_EXPLICIT_WAIT_HIGHT();

	String base_url_complex_app();
	String base_url_privat();

	String DATA_FILE();
	String DATA_FILE_PATH();
	String Oracle();
	String MySQL();
	String sqlServer();

	String MySQL_DB();
	String MySQL_DB_USER();
	String MySQL_DB_PASSWORD();


}
