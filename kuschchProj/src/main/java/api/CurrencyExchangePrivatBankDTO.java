package api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CurrencyExchangePrivatBankDTO {
        String ccy;
        String base_ccy;
        Float buy;
        Float sale;
}
