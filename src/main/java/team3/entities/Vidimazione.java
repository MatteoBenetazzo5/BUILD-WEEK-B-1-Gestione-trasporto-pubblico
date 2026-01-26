package team3.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Vidimazione {

    @Id
    @GeneratedValue
    private UUID idVidimazione;

    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mezzo_id", nullable = false)
    private MezzoDiTrasporto mezzoDiTrasporto;

    public Vidimazione() {}

   public Vidimazione(MezzoDiTrasporto mezzoDiTrasporto, LocalDate data) {
    this.mezzoDiTrasporto = mezzoDiTrasporto;
    this.data = data;

}
}
