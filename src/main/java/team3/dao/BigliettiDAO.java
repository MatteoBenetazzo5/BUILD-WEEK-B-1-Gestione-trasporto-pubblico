package team3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import team3.entities.Biglietto;
import team3.entities.Percorrenza;

import java.time.LocalDateTime;
import java.util.List;

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
        TypedQuery<Biglietto> query = em.createQuery("SELECT p FROM biglietti p", Biglietto.class);
        return query.getResultList();
    }

    public long countBigliettiVidimatiPeriodo(
            LocalDateTime from,
            LocalDateTime to
    ) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();

            Number count = (Number) em.createQuery(
                                              """
                                              SELECT COUNT(*)
                                              FROM biglietti
                                              WHERE data_vidimazione BETWEEN :from AND :to
                                              """
                                      )
                                      .setParameter("from", from)
                                      .setParameter("to", to)
                                      .getSingleResult();

            et.commit();
            return count.longValue();

        } catch (PersistenceException e) { // query fallita o connessione rotta entriamo nel catch (persistenceexception)
            if (et.isActive()) et.rollback(); //et.isactive controlla se la connessione e' ancora aperta, se lo e' rollback annulla tutte le transizioni fino a questo punto
            throw new RuntimeException("errore nel conteggio dei biglietti vidimati nel periodo", e);
        }
    }
}
