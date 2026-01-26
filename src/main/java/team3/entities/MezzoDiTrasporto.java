package team3.entities;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class MezzoDiTrasporto {

    @Id
    @GeneratedValue
    private UUID idMezzi;

    @Enumerated(EnumType.STRING)
    private TipoMezzoDiTrasporto tipoDiMezzo;

    private int capienza;
     public MezzoDiTrasporto(TipoMezzoDiTrasporto tipoDiMezzo, int capienza) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.capienza = capienza;
}}
