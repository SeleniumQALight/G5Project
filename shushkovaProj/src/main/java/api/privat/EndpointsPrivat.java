package api.privat;

public interface EndpointsPrivat {
    /**query params**/
    String baseUrl = "https://api.privatbank.ua";
    String POST_BY_USER_CURRENCY = baseUrl + "/p24api/pubinfo?exchange&json&coursid={0}";
}
