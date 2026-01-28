package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team3.entities.PuntoVendita;

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


    // -------- DELETE --------
    public boolean deleteById(UUID puntoVenditaId) {

        PuntoVendita p = findById(puntoVenditaId);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(p);
        tx.commit();

        return true;
    }

}
