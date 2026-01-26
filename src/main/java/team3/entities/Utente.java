package team3.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    private UUID id;

    private String nome;

    private String cognome;

    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    // Costruttori
    public Utente() {
    }

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    // Getter e Setter

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
}
