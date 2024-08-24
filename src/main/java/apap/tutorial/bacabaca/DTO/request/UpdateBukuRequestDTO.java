package apap.tutorial.bacabaca.DTO.request;

import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.model.Penulis;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateBukuRequestDTO extends CreateBukuRequestDTO{
    private UUID id;
}
