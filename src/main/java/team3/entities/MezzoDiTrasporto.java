package team3.entities;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class MezzoDiTrasporto {

    @Id
    @GeneratedValue
    private UUID idMezzi;

    @Enumerated(EnumType.STRING)
    private TipoMezzoDiTrasporto tipoDiMezzo;

    @OneToMany(mappedBy = "mezzo")
    private List<PeriodoDiServizio> periodo;

    private int capienza;

    protected MezzoDiTrasporto() {
    }

    public MezzoDiTrasporto(TipoMezzoDiTrasporto tipoDiMezzo, int capienza) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.capienza = capienza;
}

// getter e setter

    public UUID getIdMezzi() {
        return idMezzi;
    }

    public TipoMezzoDiTrasporto getTipoDiMezzo() {
        return tipoDiMezzo;
    }

    public void setTipoDiMezzo(TipoMezzoDiTrasporto tipoDiMezzo) {
        this.tipoDiMezzo = tipoDiMezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
    public List<PeriodoDiServizio> getPeriodo() {
        return periodo;
    }

    @Override
    public String toString() {
        return "MezzoDiTrasporto" +
                "\nidMezzi: " + idMezzi +
                ", tipoDiMezzo: " + tipoDiMezzo +
                ", periodo: " + periodo +
                ", capienza: " + capienza ;
    }
}
