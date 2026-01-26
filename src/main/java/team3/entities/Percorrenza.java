package team3.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {

    @Id
    @UuidGenerator
    @Column(name = "id_percorrenze", nullable = false, updatable = false)
    private UUID id_percorrenze;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mezzo_id", referencedColumnName = "id_mezzo", nullable = false)
    private Mezzo mezzo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratta_id", referencedColumnName = "id_tratta", nullable = false)
    private Tratta tratta;

    @Column(name = "data_ora_partenza", nullable = false)
    private LocalDate data_ora_partenza;

    @Column(name = "data_ora_arrivo", nullable = false)
    private LocalDate data_ora_arrivo;

    @Column(name = "minuti_effettivi", nullable = false)
    private int minuti_effettivi;

    protected Percorrenza() {}

    public Percorrenza(Mezzo mezzo, Tratta tratta, LocalDate data_ora_partenza, LocalDate data_ora_arrivo, int minuti_effettivi) {
        this.mezzo = mezzo;
        this.tratta = tratta;
        this.data_ora_partenza = data_ora_partenza;
        this.data_ora_arrivo = data_ora_arrivo;
        this.minuti_effettivi = minuti_effettivi;
    }

    //  Getter

    public UUID getId_percorrenze() {
        return id_percorrenze;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public LocalDate getData_ora_partenza() {
        return data_ora_partenza;
    }

    public LocalDate getData_ora_arrivo() {
        return data_ora_arrivo;
    }

    public int getMinuti_effettivi() {
        return minuti_effettivi;
    }

    //   Setter

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public void setData_ora_partenza(LocalDate data_ora_partenza) {
        this.data_ora_partenza = data_ora_partenza;
    }

    public void setData_ora_arrivo(LocalDate data_ora_arrivo) {
        this.data_ora_arrivo = data_ora_arrivo;
    }

    public void setMinuti_effettivi(int minuti_effettivi) {
        this.minuti_effettivi = minuti_effettivi;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id_percorrenze=" + id_percorrenze +
                ", data_ora_partenza=" + data_ora_partenza +
                ", data_ora_arrivo=" + data_ora_arrivo +
                ", minuti_effettivi=" + minuti_effettivi +
                '}';
    }


}
