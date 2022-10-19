package api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PrivatApiDTO {
    String ccy;
    String base_ccy;
    String buy;
    String sale;
}
