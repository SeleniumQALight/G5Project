package api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class GetDTOPB {
    String ccy ;
    String base_ccy;
    Double buy;
    Double sale;
    public GetDTOPB (){

    }
    public GetDTOPB(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    public void getCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "GetDTOPB{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
