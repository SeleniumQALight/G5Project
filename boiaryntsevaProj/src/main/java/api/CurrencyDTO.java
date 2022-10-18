package api;

public class CurrencyDTO {
    String ccy;
    String base_ccy;
    String buy;
    String sale;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String ccy, String base_ccy) {
        this.ccy=ccy;
        this.base_ccy=base_ccy;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
