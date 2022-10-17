package api;

public class P24ExchangeRateDTO {

    String ccy;
    String base_ccy;
    String buy;
    String sale;

    public P24ExchangeRateDTO() {

    }

    public P24ExchangeRateDTO(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
    }

    public String getCcy() {
        return ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }


    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "P24ExchangeRateDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy='" + buy + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }
}
