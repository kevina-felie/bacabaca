package apap.tutorial.bacabaca.DTO;

import apap.tutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BukuMapper {
    Buku createBukuRequestDTOToBuku(CreateBukuRequestDTO createBukuRequestDTO);

    Buku updateBukuRequestDTOToBuku(UpdateBukuRequestDTO updateBukuRequestDTO);

    UpdateBukuRequestDTO bukuToUpdateBukuRequestDTO(Buku buku);
}
