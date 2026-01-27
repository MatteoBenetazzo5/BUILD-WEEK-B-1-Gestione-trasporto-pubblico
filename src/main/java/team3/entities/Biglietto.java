package team3.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id_biglietto", nullable = false)
    private UUID idBiglietto;

    @Column(name = "codice_univoco", nullable = false, unique = true)
    private String codiceUnivoco;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;


    @Column(name = "data_vidimazione")
    private LocalDateTime dataVidimazione; // nullable finché non è vidimato

    @ManyToOne
    @JoinColumn(name = "punto_emissione_id", nullable = false)
    private PuntoVendita puntoVendita;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private MezzoDiTrasporto mezzo;

    public Biglietto() {
    }

    public Biglietto(String codiceUnivoco, LocalDate dataEmissione, LocalDateTime dataVidimazione, PuntoVendita puntoVendita, MezzoDiTrasporto mezzo) {
        this.codiceUnivoco = codiceUnivoco;
        this.dataEmissione = dataEmissione;
        this.dataVidimazione = dataVidimazione;
        this.puntoVendita = puntoVendita;
        this.mezzo = mezzo;
    }

    public UUID getIdBiglietto() {
        return idBiglietto;
    }

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }


    public LocalDateTime getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDateTime dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "idBiglietto=" + idBiglietto +
                ", codiceUnivoco='" + codiceUnivoco + '\'' +
                ", dataEmissione=" + dataEmissione +
                ", dataVidimazione=" + dataVidimazione +
                '}';
    }
}

