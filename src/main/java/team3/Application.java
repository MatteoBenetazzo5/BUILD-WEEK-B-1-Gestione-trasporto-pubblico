package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team3.dao.*;
import team3.entities.*;
import team3.exceptions.NotFoundIdException;

import java.time.LocalDate;
import java.util.UUID;

import static team3.entities.TipoMezzoDiTrasporto.AUTOBUS;
import static team3.entities.TipoMezzoDiTrasporto.TRAM;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblicopu");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);

        // ---------- CREO 4 UTENTI ----------

        Utente u1 = new Utente("Mario", "Rossi");
        Utente u2 = new Utente("Luigi", "Bianchi");
        Utente u3 = new Utente("Dario", "Verdi");
        Utente u4 = new Utente("Anna", "Viola");

     //   utenteDAO.saveUtente(u1);
      //  utenteDAO.saveUtente(u2);
       //  utenteDAO.saveUtente(u3);
       //  utenteDAO.saveUtente(u4);


        // ---------- CREO 2 TESSERE ----------

        Tessera t1 = new Tessera();
        t1.setCodiceTessera("TESSERA-001");
        t1.setDataEmissione(LocalDate.now());
        t1.setDataScadenza(LocalDate.now().plusYears(1));
        t1.setUtente(u1);

        Tessera t2 = new Tessera();
        t2.setCodiceTessera("TESSERA-002");
        t2.setDataEmissione(LocalDate.now());
        t2.setDataScadenza(LocalDate.now().plusYears(1));
        t2.setUtente(u2);

      // tesseraDAO.saveTessera(t1);
      //  tesseraDAO.saveTessera(t2);




        // MEZZI DI TRASPORTO
        // salvo un mezzo di trasporto
        MezziDiTrasportoDAO mezzoDAO = new MezziDiTrasportoDAO(em);


        //MezzoDiTrasporto mezzo = new MezzoDiTrasporto(
            //    TRAM,
           //     50
     //   );
      //  mezzoDAO.save(mezzo);

        //PUNTO DI VENDITA
        PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);

        //PuntoVendita puntoVendita1 = new PuntoVendita(StatoPuntoVendita.ATTIVO,TipoPuntoVendita.DISTRIBUTORE);
        PuntoVendita puntoVendita2 = new PuntoVendita(StatoPuntoVendita.FUORI_SERVIZIO,TipoPuntoVendita.DISTRIBUTORE);
        PuntoVendita puntoVendita3 = new PuntoVendita(null, TipoPuntoVendita.RIVENDITORE);
        PuntoVendita puntoVendita4 = new PuntoVendita(null,TipoPuntoVendita.RIVENDITORE);

        //puntoVenditaDAO.save(puntoVendita1);
       // puntoVenditaDAO.save(puntoVendita2);
       // puntoVenditaDAO.save(puntoVendita3);
       // puntoVenditaDAO.save(puntoVendita4);

        //BIGLIETTO
        PuntoVendita puntoVendita1 = puntoVenditaDAO.findById(UUID.fromString("03ca3045-4470-4943-be2d-03339f96b5be"));
        MezzoDiTrasporto mezzo = mezzoDAO.findById(UUID.fromString("b9da2f59-3544-4bbf-affa-78a9c78c4f97"));
        BigliettiDAO bigliettiDAO = new BigliettiDAO(em);

        Biglietto biglietto1 = new Biglietto("123ABC",LocalDate.now(), null,puntoVendita1,mezzo);
            //bigliettiDAO.save(biglietto1);

        // Cerco il tipo di mezzo dato un id presente nel DB
        try {
            TipoMezzoDiTrasporto tipo = mezzoDAO.getTipoById(UUID.fromString("690ec275-f589-4b7d-bba8-7efe9bd7737f"));
            System.out.println("Il tipo del mezzo è: " + tipo);
        } catch (NotFoundIdException ex) {
            System.out.println(ex.getMessage());
        }

        // PERIODO DI SERVIZIO

        PeriodiDiServizioDAO periodiDAO = new PeriodiDiServizioDAO(em);
        // uso l'uuid di un record che ho nel DB
        UUID idMezzo = UUID.fromString("690ec275-f589-4b7d-bba8-7efe9bd7737f");
        MezzoDiTrasporto mezzo1 = mezzoDAO.findById(idMezzo);

        PeriodoDiServizio p1 = new PeriodoDiServizio(
                StatoServizio.IN_SERVIZIO,
                mezzo1,
                LocalDate.of(2026, 1, 6),
                null,
                null
        );

     //   periodiDAO.save(p1);

        try {
            // Lista dei periodi di servizio dato l'id
            System.out.println("\nPeriodi di servizio: ");
            periodiDAO.getPeriodiDiServizio(idMezzo).forEach(p ->
                    System.out.println("Date di inizio servizio: " + p.getDataInizio())
            );

            // Lista dei periodi di manutenzione dato l'id
            System.out.println("\nPeriodi di manutenzione: ");
            periodiDAO.getPeriodiDiManutenzione(idMezzo).forEach(p ->
                    System.out.println("Causa manutenzione: " + p.getCausaManutenzione() +
                            "\nDate di inizio manutenzione: " + p.getDataInizio() +
                            " date di fine: " + p.getDataFine())
            );

        } catch (NotFoundIdException ex) {
            System.out.println(ex.getMessage());
        }



}}
