package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import team3.entities.Abbonamento;
import team3.entities.Biglietto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class AbbonamentoDAO {

    private final EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Abbonamento a) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(a);
        transaction.commit();

        System.out.println("L'abbonamento con il codice: " + a.getCodiceUnivoco()+ " "  + " è stato correttamente salvato!");
    }
    // 1) VALIDITA ABBONAMENTO
    // ritorna true se esiste almeno un abbonamento attivo per quella tessera in quella data
    public boolean isAbbonamentoValido(String codiceTessera, LocalDate dataControllo) {
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(a) " +
                                    "FROM Abbonamento a " +
                                    "WHERE a.tessera.codiceTessera = :cod " +
                                    "AND :d BETWEEN a.dataInizio AND a.dataFine",
                            Long.class
                    )
                    .setParameter("cod", codiceTessera)
                    .setParameter("d", dataControllo)
                    .getSingleResult();

            return count != null && count > 0;

        } catch (NoResultException e) {
            return false;
        }
    }

    public long abbonamentiEmessiPeriodo(LocalDate start,LocalDate end){
        TypedQuery<Long> query= em.createQuery(
                "SELECT COUNT (a) FROM Abbonamento a WHERE a.dataInizio BETWEEN :start AND :end",
                Long.class
        );
        query.setParameter("start",start);
        query.setParameter("end",end);
        return query.getSingleResult();
    }

    // 2) NUM ABBONAMENTI EMESSI PER PUNTO EMISSIONE in un PERIODO (uso dataInizio come emissione)
    public long countEmessiPerPuntoVendita(UUID puntoVenditaId, LocalDate start, LocalDate end) {
        return em.createQuery(
                        "SELECT COUNT(a) " +
                                "FROM Abbonamento a " +
                                "WHERE a.puntoVendita.idPuntiVendita = :pid " +
                                "AND a.dataInizio BETWEEN :start AND :end",
                        Long.class
                )
                .setParameter("pid", puntoVenditaId)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

    // 3) RECUPERA TUTTI GLI ABBONAMENTI
    public List<Abbonamento> findAllAbbonamenti() {
        return em.createQuery(
                "SELECT a FROM Abbonamento a",
                Abbonamento.class
        ).getResultList();
    }

}
