package api;

public interface EndPointsPB {
    String baseUrlPB = "https://api.privatbank.ua";
    String GET_FOR_CURRENCY = baseUrlPB + "/p24api/pubinfo?exchange&json&coursid={0}";

    String baseUrlUIPrivatBank = "https://privatbank.ua/";

}
