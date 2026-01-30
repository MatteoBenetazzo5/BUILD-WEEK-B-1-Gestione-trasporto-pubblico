package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import team3.entities.Biglietto;
import team3.entities.Percorrenza;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettiDAO {

    private final EntityManager em;

    public BigliettiDAO(EntityManager em) {
        this.em = em;
    }

   // public List<Biglietto> findBigliettiVidimatiPerMezzo(
         //   UUID mezzoId,
        //    LocalDate dataEmissione
  //  )
   public void save(Biglietto b) {

       EntityTransaction transaction = em.getTransaction();
       transaction.begin();
       em.persist(b);
       transaction.commit();

       System.out.println("Il biglietto con id: " + b.getIdBiglietto()+ " "  + " è stato correttamente salvato!");
   }
    public List<Biglietto> findAllBiglietti() {
        TypedQuery<Biglietto> query = em.createQuery("SELECT b FROM Biglietto b", Biglietto.class);
        return query.getResultList();
    }

    public long countBigliettiVidimatiPeriodo(
            LocalDate from,
            LocalDate to) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();

            Number count = (Number) em.createQuery(
                                              """
                                              SELECT COUNT(b)
                                              FROM Biglietto b
                                              WHERE b.dataVidimazione BETWEEN :from AND :to
                                              """
                                      )
                                      .setParameter("from", from)
                                      .setParameter("to", to)
                                      .getSingleResult();

            et.commit();
            return count.longValue();

        } catch (PersistenceException e) { // query fallita o connessione rotta entriamo nel catch (persistenceexception)
            if (et.isActive()) et.rollback(); //et.isactive controlla se la connessione è ancora aperta, se lo è rollback annulla tutte le transizioni fino a questo punto
            throw new RuntimeException("errore nel conteggio dei biglietti vidimati nel periodo", e);
        }
    }
    public long findBigliettiVidimatiPerMezzo(UUID mezzoId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) " +
                        "FROM Biglietto b " +
                        "WHERE b.mezzo.id = :mezzoId " +
                        "AND b.dataVidimazione IS NOT NULL",
                Long.class
        );

        query.setParameter("mezzoId", mezzoId);

        return query.getSingleResult();
    }

    public long findBigliettiEmessiPeriodo(LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) " +
                        "FROM Biglietto b " +
                        "WHERE b.dataEmissione BETWEEN :inizio AND :fine",
                Long.class
        );
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        return query.getSingleResult();
    }


    public void delete(Biglietto b) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(b);
        transaction.commit();

        System.out.println("Il biglietto con id: " + b.getIdBiglietto()+ " "  + " è stato correttamente eliminato!");
    }

}
