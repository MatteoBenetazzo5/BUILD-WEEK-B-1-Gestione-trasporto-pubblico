package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import team3.entities.Biglietto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BigliettiDAO {

    private final EntityManager em;

    public BigliettiDAO(EntityManager em) {
        this.em = em;
    }

    public List<Biglietto> findBigliettiVidimatiPerMezzo(
            UUID mezzoId,
            LocalDate dataEmissione
    ) {
        EntityTransaction et = em.getTransaction();

}
