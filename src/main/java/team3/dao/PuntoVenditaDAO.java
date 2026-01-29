package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team3.entities.MezzoDiTrasporto;
import team3.entities.PuntoVendita;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PuntoVenditaDAO {
    private final EntityManager entityManager;

    public PuntoVenditaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // --------- SAVE ---------
    public void save(PuntoVendita p) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(p);
        transaction.commit();
        System.out.println("Il punto di vendita con id: " + p.getId()+ " "  + " è stato correttamente salvato!");
    }

    // --------- FIND BY ID  ---------
    public PuntoVendita findById(UUID id) {

        PuntoVendita p = entityManager.find(PuntoVendita.class, id);

        if (p == null) {
            System.out.println("Il punto vendita con id: " + id + " non è stato trovato");
            return null;
        }

        return p;
    }

    //CONTEGGIO BIGLIETTI PER PUNTO DI EMISSIONE
    public long countBigliettiPerPuntoEmissione(UUID puntoId) {

        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) " +
                        "FROM Biglietto b " +
                        "WHERE b.puntoEmissione.id = :puntoId",
                Long.class
        );

        query.setParameter("puntoId", puntoId);

        return query.getSingleResult();
    }

    //CONTEGGIO ABBONAMENTI PER PUNTO DI EMISSIONE
    public long countAbbonamentiPerPuntoEmissione(UUID puntoId) {

        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(b) " +
                        "FROM Abbonamento a " +
                        "WHERE b.puntoEmissione.id = :puntoId",
                Long.class
        );

        query.setParameter("puntoId", puntoId);

        return query.getSingleResult();
    }

    // -------- DELETE --------
    public boolean deleteById(UUID puntoVenditaId) {

        PuntoVendita p = findById(puntoVenditaId);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(p);
        tx.commit();

        return true;
    }

    // prendo la lista di tutti i punti vendita

    public List<PuntoVendita> findAllPuntiVendita() {
        TypedQuery<PuntoVendita> query = entityManager.createQuery
                ("SELECT p FROM PuntoVendita p", PuntoVendita.class);
        return query.getResultList();
    }

}
