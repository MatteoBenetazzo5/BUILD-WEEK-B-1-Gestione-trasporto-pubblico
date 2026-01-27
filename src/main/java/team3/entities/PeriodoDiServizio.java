package team3.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "periodo_di_servizio")
public class PeriodoDiServizio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING) // IN_SERVIZIO, IN_MANUTENZIONE
    private StatoServizio statoServizio;

    @ManyToOne
    @JoinColumn(name = "idMezzi", nullable = false)
    private MezzoDiTrasporto mezzo;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    // lascio il default nullable true perché non si sa a priori la data di fine della manutenzione
    @Column(name = "data_fine")
    private LocalDate dataFine;

    @Column(name = "causa_manutenzione")
    private String causaManutenzione;

    public PeriodoDiServizio() {
    }
    // getter e setter

    public PeriodoDiServizio(StatoServizio statoServizio, MezzoDiTrasporto mezzo, LocalDate dataInizio, LocalDate dataFine) {
        this.statoServizio = statoServizio;
        this.mezzo = mezzo;
        // Faccio dei controlli sulle date (la data di inizio non deve essere null
        // e la data di fine deve essere successiva a quella di inizio
        if (dataInizio == null) {
            throw new IllegalArgumentException("Devi inserire una data di inizio!");
        }

        if (dataFine != null && dataFine.isBefore(dataInizio)) {
            throw new IllegalArgumentException("Attenzione! La data di fine deve essere " +
                    "successiva alla data di inizio!");
        }
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public UUID getId() {
        return id;
    }

    public StatoServizio getStatoServizio() {
        return statoServizio;
    }

    public void setStatoServizio(StatoServizio statoServizio) {
        this.statoServizio = statoServizio;
    }

    public MezzoDiTrasporto getMezzo() {
        return mezzo;
    }

    public void setMezzo(MezzoDiTrasporto mezzo) {
        this.mezzo = mezzo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getCausaManutenzione() {
        return causaManutenzione;
    }

    public void setCausaManutenzione(String causaManutenzione) {
        this.causaManutenzione = causaManutenzione;
    }

}

