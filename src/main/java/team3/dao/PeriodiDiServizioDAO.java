package team3.dao;

import jakarta.persistence.EntityManager;
import team3.entities.PeriodoDiServizio;
import team3.entities.StatoServizio;
import java.util.List;
import java.util.UUID;

public class PeriodiDiServizioDAO {

    private final EntityManager entityManager;

    public PeriodiDiServizioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // Tenere traccia dei periodi di servizio e manutenzione di ogni mezzo.
    // Creo un metodo per prendere i periodi di servizio e controllare lo stato e li ordino per data di inizio

    private List<PeriodoDiServizio> getPeriodi(UUID idmezzi, StatoServizio stato) {
        return entityManager.createQuery(
                        "SELECT p FROM PeriodoDiServizio p " +
                                "WHERE p.idmezzi = :id " +
                                "AND p.statoServizio = :stato " +
                                "ORDER BY p.data_inizio DESC",
                        PeriodoDiServizio.class
                )
                .setParameter("id", idmezzi)
                .setParameter("stato",stato)
                .getResultList();
    }
        // riutilizzo il metodo sopra con i due stati che mi servono (IN_MANUTENZIONE, IN_SERVIZIO)
    public List<PeriodoDiServizio> getPeriodiDiManutenzione(UUID idmezzi) {
        return getPeriodi(idmezzi, StatoServizio.IN_MANUTENZIONE);
    }

    public List<PeriodoDiServizio> getPeriodiDiServizio(UUID idmezzi) {
        return getPeriodi(idmezzi, StatoServizio.IN_SERVIZIO);
    }
}
