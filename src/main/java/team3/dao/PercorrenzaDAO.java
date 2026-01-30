package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team3.entities.MezzoDiTrasporto;
import team3.entities.Percorrenza;
import team3.entities.Tratta;
import team3.exceptions.NotFoundIdException;

import java.util.List;
import java.util.UUID;

public class PercorrenzaDAO {
    private EntityManager em;

    public PercorrenzaDAO(EntityManager em) {
        this.em = em;
    }

    // 1) SALVO UNA PERCORRENZA
    public void save(Percorrenza newPercorrenza) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newPercorrenza);

        transaction.commit();
        System.out.println("La percorrenza " + newPercorrenza.getId_percorrenze() + " è stata salvata correttamente!");
    }

    // 2) TRATTA: CREO E SALVO TRATTA
    public void saveTratta(Tratta newTratta) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newTratta);

        transaction.commit();
        System.out.println("La tratta " + newTratta.getId_tratta() + " è stata salvata correttamente!");
    }

    // 3)  CERCO TRATTA PER ID
    public Tratta findTrattaById(UUID trattaId) {
        Tratta found = em.find(Tratta.class, trattaId);
        if (found == null) throw new NotFoundIdException(trattaId);
        return found;
    }

    // 4) CREA E SALVA PERCORRENZA COLLEGANDO UNA TRATTA GIÀ ESISTENTE

    public void createAndSave(MezzoDiTrasporto mezzo, UUID trattaId, int minutiEffettivi) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Tratta tratta = em.find(Tratta.class, trattaId);
        if (tratta == null) {
            transaction.rollback();
            throw new NotFoundIdException(trattaId);
        }

        Percorrenza p = new Percorrenza(mezzo, tratta, minutiEffettivi);
        em.persist(p);

        transaction.commit();

        System.out.println("Percorrenza creata correttamente! tratta_id=" + trattaId);
    }

    // 5) CREA TUTTO INSIEME: CREA TRATTA + CREA PERCORRENZA

    public void createTrattaAndPercorrenza(MezzoDiTrasporto mezzo,
                                           String zonaPartenza,
                                           String capolinea,
                                           int tempoPrevistoMinuti,
                                           int minutiEffettivi) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Tratta tratta = new Tratta(zonaPartenza, capolinea, tempoPrevistoMinuti);
        em.persist(tratta);

        Percorrenza p = new Percorrenza(mezzo, tratta, minutiEffettivi);
        em.persist(p);

        transaction.commit();

        System.out.println("Tratta + Percorrenza create correttamente! tratta_id=" + tratta.getId_tratta());
    }

    // 6) PERCORRENZA: RICERCA PER ID
    public Percorrenza findById(UUID percorrenzaId) {
        Percorrenza found = em.find(Percorrenza.class, percorrenzaId);
        if (found == null) throw new NotFoundIdException(percorrenzaId);
        return found;
    }

    // 7) PERCORRENZA: TROVO TUTTE
    public List<Percorrenza> findAllPercorrenze() {
        TypedQuery<Percorrenza> query = em.createQuery("SELECT p FROM Percorrenza p", Percorrenza.class);
        return query.getResultList();
    }

    // 8) ADMIN : TEMPO MEDIO EFFETTIVO su tratta+mezzo
    public double getTempoMedioEffettivo(MezzoDiTrasporto mezzo, UUID trattaId) {
        Tratta tratta = findTrattaById(trattaId);

        TypedQuery<Double> query = em.createQuery(
                "SELECT AVG(p.minuti_effettivi) FROM Percorrenza p WHERE p.mezzo = :mezzo AND p.tratta = :tratta",
                Double.class
        );
        query.setParameter("mezzo", mezzo);
        query.setParameter("tratta", tratta);

        Double res = query.getSingleResult();
        return res == null ? 0.0 : res;

    }
    // Conto il numero delle volte che un mezzo ha percorso una tratta
    public long countPercorrenze(MezzoDiTrasporto mezzo, UUID trattaId) {
        Tratta tratta = findTrattaById(trattaId);

        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) " +
                        "FROM Percorrenza p " +
                        "WHERE p.mezzo = :mezzo AND p.tratta = :tratta",
                Long.class
        );

        query.setParameter("mezzo", mezzo);
        query.setParameter("tratta", tratta);

        Long result = query.getSingleResult();
        return result == null ? 0L : result;
    }


    // 9) DELETE
    public void findByIdAndDelete(UUID percorrenzaId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Percorrenza found = em.find(Percorrenza.class, percorrenzaId);
        if (found == null) {
            transaction.rollback();
            throw new NotFoundIdException(percorrenzaId);
        }

        em.remove(found);

        transaction.commit();
        System.out.println("La percorrenza " + percorrenzaId + " è stata cancellata correttamente!");
    }
}
