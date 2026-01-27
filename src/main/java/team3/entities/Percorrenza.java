package team3.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {

    @Id
    @UuidGenerator
    @Column(name = "id_percorrenze", nullable = false, updatable = false)
    private UUID id_percorrenze;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mezzo_id", referencedColumnName = "idMezzi", nullable = false)
    private MezzoDiTrasporto mezzo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratta_id", referencedColumnName = "id_tratta", nullable = false)
    private Tratta tratta;

    @Column(name = "minuti_effettivi", nullable = false)
    private int minuti_effettivi;

    protected Percorrenza() {
    }

    public Percorrenza(MezzoDiTrasporto mezzo, Tratta tratta, int minuti_effettivi) {
        this.mezzo = mezzo;
        this.tratta = tratta;
        this.minuti_effettivi = minuti_effettivi;
    }

    //  Getter

    public UUID getId_percorrenze() {
        return id_percorrenze;
    }

    public MezzoDiTrasporto getMezzo() {
        return mezzo;
    }

    public void setMezzo(MezzoDiTrasporto mezzo) {
        this.mezzo = mezzo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    //   Setter

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public int getMinuti_effettivi() {
        return minuti_effettivi;
    }

    public void setMinuti_effettivi(int minuti_effettivi) {
        this.minuti_effettivi = minuti_effettivi;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id_percorrenze=" + id_percorrenze +
                ", minuti_effettivi=" + minuti_effettivi +
                '}';
    }


}
