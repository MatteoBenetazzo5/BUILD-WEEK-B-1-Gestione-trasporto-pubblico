package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team3.entities.Tessera;
import team3.entities.Tratta;

import java.util.List;
import java.util.UUID;

public class TrattaDAO {
    private final EntityManager entityManager;

    public TrattaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveTratta(Tratta newTratta) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newTratta);
        transaction.commit();

        System.out.println("La tratta con id: " + newTratta.getId_tratta() + " è stata salvata correttamente!");
    }

    // 7) TRATTA: TROVO TUTTE
    public List<Tratta> findAllTratte() {
        TypedQuery<Tratta> query = entityManager.createQuery("SELECT t FROM Tratta t", Tratta.class);
        return query.getResultList();
    }

    public boolean deleteTrattaById(UUID idTratta) {

        Tratta t = entityManager.find(Tratta.class, idTratta);

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
