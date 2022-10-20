package api.privatBank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PrivatDTO {
    String ccy;
    @JsonProperty("base_ccy")
    String baseCcy;
    String buy;
    String sale;

    public PrivatDTO(String ccy, String baseCcy) {
        this.ccy = ccy;
        this.baseCcy = baseCcy;
    }
}
