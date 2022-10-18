package apiPB;

public class PB_DTO{
        String ccy;
        String base_ccy;
        String buy;
        String sale;

        public PB_DTO() {
        }
    public PB_DTO(String ccy, String base_ccy) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;

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
    @Override
    public String toString() {
        return "PB_DTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy='" + buy + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }
}



