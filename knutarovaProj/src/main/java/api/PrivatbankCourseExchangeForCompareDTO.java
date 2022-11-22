package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PrivatbankCourseExchangeForCompareDTO {
    String ccy;
    String base_ccy;
    String buy;
    String sale;

    public PrivatbankCourseExchangeForCompareDTO(String buy, String sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }
}
