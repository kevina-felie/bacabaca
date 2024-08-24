package apap.tutorial.bacabaca.DTO.request;

import apap.tutorial.bacabaca.model.Penerbit;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateBukuRequestDTO {
    private String judul;
    private String tahunTerbit;
    private BigDecimal harga;
    private Penerbit penerbit;
}