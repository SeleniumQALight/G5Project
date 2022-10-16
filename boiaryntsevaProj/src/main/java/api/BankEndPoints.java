package api;

public interface BankEndPoints {

    String baseURL="https://api.privatbank.ua/p24api/";
    String exchangeURL=baseURL+"pubinfo?exchange&json&coursid={0}";
}
