package team3.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "punto_vendita")
public class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPuntoVendita tipoPuntoVendita;

    // lascio il nullable = true di default perché lo stato lo possiede solo il tipo DISTRIBUTORE
    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private StatoPuntoVendita statoPuntoVendita;

    public PuntoVendita() {
    }
    // getter e setter

    public UUID getId() {
        return id;
    }


    public TipoPuntoVendita getTipoPuntoVendita() {
        return tipoPuntoVendita;
    }

    public StatoPuntoVendita getStatoPuntoVendita() {
        return statoPuntoVendita;
    }

    // Aggiungo dei controlli ai setter, perché solo il tipo DISTRIBUTORE può avere uno stato che non è null

    public void setTipoPuntoVendita(TipoPuntoVendita tipoPuntoVendita) {
        if (tipoPuntoVendita != TipoPuntoVendita.DISTRIBUTORE && this.statoPuntoVendita != null) {
            throw new IllegalArgumentException("Il tipo RIVENDITORE deve avere lo stato null");
        }
        this.tipoPuntoVendita = tipoPuntoVendita;
    }

    public void setStatoPuntoVendita(StatoPuntoVendita statoPuntoVendita) {
        if (this.tipoPuntoVendita != TipoPuntoVendita.DISTRIBUTORE && statoPuntoVendita != null) {
            throw new IllegalArgumentException("Il tipo RIVENDITORE deve avere lo stato null");
        }
        this.statoPuntoVendita = statoPuntoVendita;
    }

    public PuntoVendita(StatoPuntoVendita statoPuntoVendita, TipoPuntoVendita tipoPuntoVendita) {
        this.tipoPuntoVendita = tipoPuntoVendita;
        // se il tipoPuntoVendita = DISTRIBUTORE allora lo statoPuntoVendita va bene e lo salvo
        if (tipoPuntoVendita == TipoPuntoVendita.DISTRIBUTORE) {
            this.statoPuntoVendita = statoPuntoVendita;
            // se non è DISTRIBUTORE e gli viene assegnato un valore che non è null, compare un exception
        } else if (statoPuntoVendita != null) {
            throw new IllegalArgumentException("Attenzione: solo i distributori hanno uno stato!");
        }

    }
}
