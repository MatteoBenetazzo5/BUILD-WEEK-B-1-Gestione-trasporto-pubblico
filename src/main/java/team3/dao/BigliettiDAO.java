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
        try {
            et.begin();

            List result = em.createNativeQuery(
                                               """
                                               SELECT *
                                               FROM biglietto
                                                 WHERE data_emissione = :dataEmissione
                                                 AND data_vidimazione IS NOT NULL
                                               """,
                                               Biglietto.class
                                       )
                                       .setParameter("mezzoId", mezzoId)
                                       .setParameter("dataEmissione", dataEmissione)
                                       .getResultList();

            et.commit();
            return result;

        } catch (PersistenceException e) {
            if (et.isActive()) et.rollback();
            throw new RuntimeException("Errore nel recupero dei biglietti vidimati per mezzo", e);
        }
    }
    public long countBigliettiVidimatiPeriodo(
            LocalDateTime from,
            LocalDateTime to
    ) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();

            Number count = (Number) em.createNativeQuery(
                                              """
                                              SELECT COUNT(*)
                                              FROM biglietti
                                              WHERE data_vidimazione BETWEEN :from AND :to
                                              """
                                      )
                                      .setParameter("from", from)
                                      .setParameter("to", to)
                                      .getSingleResult();

            et.commit();
            return count.longValue();

        } catch (PersistenceException e) {
            if (et.isActive()) et.rollback();
            throw new RuntimeException("errore nel conteggio dei biglietti vidimati nel periodo", e);
        }
    }
}
