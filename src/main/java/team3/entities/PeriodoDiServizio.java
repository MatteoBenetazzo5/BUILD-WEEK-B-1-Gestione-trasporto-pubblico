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

    public PeriodoDiServizio(StatoServizio statoServizio, MezzoDiTrasporto mezzo, LocalDate dataInizio,
                             LocalDate dataFine, String causaManutenzione) {
        this.statoServizio = statoServizio;
        this.mezzo = mezzo;

        // Faccio dei controlli
        // Un mezzo di trasporto in servizio ha solo la data di inizio. Non ha la data fine né la causa di manutenzione
        // Il mezzo in manutenzione invece ha data inizio, data fine e causa manutenzione.
        // La data di fine manutenzione deve essere posteriore alla data di inizio manutenzione
        if (dataInizio == null) {
            throw new IllegalArgumentException("Devi inserire una data di inizio!");
        }

        switch (statoServizio) {
            case IN_SERVIZIO -> {
                if (dataFine != null ) {
                    throw new IllegalArgumentException(
                            "Se il mezzo è in servizio, la data di fine servizio deve essere null.");
                }
                if (causaManutenzione != null && !causaManutenzione.isBlank()) {
                    throw new IllegalArgumentException(
                            "La causa di manutenzione di un mezzo in servizio deve essere null.");
                }
            }
            case IN_MANUTENZIONE -> {
                if (dataFine != null && dataFine.isBefore(dataInizio)) {
                    throw new IllegalArgumentException(
                            "La data di fine manutenzione deve essere posteriore alla data di inizio.");
                }
                if (causaManutenzione == null || causaManutenzione.isBlank()) {
                    throw new IllegalArgumentException(
                            "Devi aggiungere la causa della manutenzione!"
                    );
                }
            }
        }

        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.causaManutenzione = causaManutenzione;

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

    @Override
    public String toString() {
        return "PeriodoDiServizio" +
                "\nid: " + id +
                ", statoServizio: " + statoServizio +
                ", mezzo: " + mezzo +
                ", dataInizio: " + dataInizio +
                ", dataFine: " + dataFine +
                ", causaManutenzione: '" + causaManutenzione ;
    }
}

