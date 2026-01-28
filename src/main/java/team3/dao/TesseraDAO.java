package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team3.entities.Percorrenza;
import team3.entities.Tessera;

import java.util.List;
import java.util.UUID;

public class TesseraDAO {
    private final EntityManager entityManager;

    public TesseraDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveTessera(Tessera newTessera) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newTessera);
        transaction.commit();

        System.out.println("La tessera con il codice: " + newTessera.getCodiceTessera() + " è stata salvata correttamente!");
    }

    public boolean isValid(String codiceTessera) {

        TypedQuery<Tessera> query = entityManager.createQuery(
                "SELECT t FROM Tessera t " +
                        "WHERE t.codiceTessera = :cod AND t.dataScadenza >= CURRENT_DATE",
                Tessera.class
        );

        query.setParameter("cod", codiceTessera);

       return !query.getResultList().isEmpty();
    }

    // 7) TESSERA: TROVO TUTTE
    public List<Tessera> findAllTessere() {
        TypedQuery<Tessera> query = entityManager.createQuery("SELECT t FROM Tessera t", Tessera.class);
        return query.getResultList();
    }

    public Tessera findByCodiceTessera(String codiceTessera) {

        TypedQuery<Tessera> query = entityManager.createQuery(
                "SELECT t FROM Tessera t WHERE t.codiceTessera = :cod",
                Tessera.class
        );

        query.setParameter("cod", codiceTessera);

        List<Tessera> risultati = query.getResultList();

        if (risultati.isEmpty()) {
            return null;
        } else {
            return risultati.getFirst();
        }
    }



    public boolean deleteTesseraById(UUID idTessera) {

        Tessera t = entityManager.find(Tessera.class, idTessera);

        if (t == null) {
            return false;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(t);
        transaction.commit();

        return true;
    }


}
