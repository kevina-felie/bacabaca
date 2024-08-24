package apap.tutorial.bacabaca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku")
public class Buku {
    @Id
    private UUID id = UUID.randomUUID();

    @NotNull
    @Size(max = 100)
    @Column(name = "judul", nullable = false)
    private String judul;

    @NotNull
    @Size(max = 4)
    @Column(name = "tahun_terbit", nullable = false)
    private String tahunTerbit;

    @NotNull
    @Column(name = "harga", nullable = false)
    private BigDecimal harga;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penerbit", referencedColumnName = "idPenerbit")
    private Penerbit penerbit;

    @ManytoMany
    @JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_penulis"))
    List<Penulis> listPenulis;
}
