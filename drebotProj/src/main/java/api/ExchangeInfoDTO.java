package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeInfoDTO {
    String ccy;
    @JsonProperty("base_ccy")
    String baseCcy;
    String buy;
    String sale;

    public ExchangeInfoDTO() {
    }

    public ExchangeInfoDTO(String ccy, String baseCcy) {
        this.ccy = ccy;
        this.baseCcy = baseCcy;
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

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    @Override
    public String toString() {
        return "ExchangeInfoDTO{" +
                "ccy='" + ccy + '\'' +
                ", baseCcy='" + baseCcy + '\'' +
                ", buy='" + buy + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }
}
