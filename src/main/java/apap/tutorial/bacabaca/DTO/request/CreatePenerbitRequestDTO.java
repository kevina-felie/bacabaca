package apap.tutorial.bacabaca.DTO.request;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatePenerbitRequestDTO {
    private String namaPenerbit;
    private String alamat;
    private String email;
}
