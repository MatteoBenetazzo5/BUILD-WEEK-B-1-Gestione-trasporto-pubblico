package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team3.entities.Utente;

import java.util.UUID;

public class UtenteDAO {
    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUtente(Utente newUtente) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUtente);
        transaction.commit();

        System.out.println("L'utente " + newUtente.getNome() + " è stato salvato correttamente!");
    }

    public boolean deleteUtenteById(UUID idUtente) {

        Utente u = entityManager.find(Utente.class, idUtente);

        if (u == null) {
            return false;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        if (u.getTessera() != null) {
            entityManager.remove(u.getTessera());
        }

        entityManager.remove(u);
        transaction.commit();

        return true;
    }


}
