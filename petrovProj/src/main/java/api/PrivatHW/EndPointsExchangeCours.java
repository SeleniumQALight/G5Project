package api.PrivatHW;

public interface EndPointsExchangeCours {
    String baseUrl = "https://api.privatbank.ua/p24api/pubinfo?";
    String EXCHANGE = baseUrl + "exchange&json&coursid={0}";

}
