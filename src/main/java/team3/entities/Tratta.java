package team3.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @UuidGenerator
    @Column(name = "id_tratta", nullable = false, updatable = false)
    private UUID id_tratta;

    @Column(name = "zona_partenza", nullable = false, length = 50)
    private String zona_partenza;

    @Column(name = "capolinea", nullable = false, length = 50)
    private String capolinea;

    @Column(name = "tempo_previsto_minuti", nullable = false)
    private int tempo_previsto_minuti;

    @OneToMany(mappedBy = "tratta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Percorrenza> percorrenze = new HashSet<>();

    protected Tratta() {
    }

    public Tratta(String zona_partenza, String capolinea, int tempo_previsto_minuti) {
        this.zona_partenza = zona_partenza;
        this.capolinea = capolinea;
        this.tempo_previsto_minuti = tempo_previsto_minuti;
    }

    // Getter

    public UUID getId_tratta() {
        return id_tratta;
    }

    public String getZona_partenza() {
        return zona_partenza;
    }

    public void setZona_partenza(String zona_partenza) {
        this.zona_partenza = zona_partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    // Setter

    public int getTempo_previsto_minuti() {
        return tempo_previsto_minuti;
    }

    public void setTempo_previsto_minuti(int tempo_previsto_minuti) {
        this.tempo_previsto_minuti = tempo_previsto_minuti;
    }

    public Set<Percorrenza> getPercorrenze() {
        return percorrenze;
    }

    // helper
    public void addPercorrenza(Percorrenza p) {
        percorrenze.add(p);
        p.setTratta(this);
    }

    public void removePercorrenza(Percorrenza p) {
        percorrenze.remove(p);
        p.setTratta(null);
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id_tratta=" + id_tratta +
                ", zona_partenza='" + zona_partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempo_previsto_minuti=" + tempo_previsto_minuti +
                '}';
    }


}

