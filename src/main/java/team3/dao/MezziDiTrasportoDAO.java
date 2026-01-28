package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import team3.entities.TipoMezzoDiTrasporto;
import team3.entities.MezzoDiTrasporto;
import team3.exceptions.NotFoundIdException;

import java.util.UUID;


public class MezziDiTrasportoDAO {

    private final EntityManager entityManager;

    public MezziDiTrasportoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    // salvo un mezzo di trasporto
    public void save(MezzoDiTrasporto newMezzo) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(newMezzo);

        transaction.commit();

        System.out.println("Il mezzo con id: " + newMezzo.getIdMezzi()+ " "  + " è stato correttamente salvato!");
    }

    // trovo un mezzo di trasporto per ID
    public MezzoDiTrasporto findById(UUID idMezzi) {
        MezzoDiTrasporto found = entityManager.find(MezzoDiTrasporto.class, idMezzi);
        if (found == null) throw new NotFoundIdException(idMezzi);
        return found;
    }

    // cancello un mezzo di trasporto
    public void findByIdAndDelete(UUID idMezzi) {
        MezzoDiTrasporto found = this.findById(idMezzi);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Il mezzo di trasporto con id: " + idMezzi + " è stato eliminato con successo!");
    }

    // Leggo il tipo del mezzo (TRAM, AUTOBUS) dato un ID
    public TipoMezzoDiTrasporto getTipoById(UUID idMezzi) {
        return findById(idMezzi).getTipoDiMezzo();
    }


}