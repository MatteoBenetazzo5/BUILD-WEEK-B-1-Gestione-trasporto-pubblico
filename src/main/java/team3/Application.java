package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team3.dao.*;
import team3.entities.*;
import team3.exceptions.NotFoundIdException;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
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


        MezzoDiTrasporto mezzo = new MezzoDiTrasporto(
                TRAM,
                50
        );
        MezzoDiTrasporto mezzo2 = new MezzoDiTrasporto(
                AUTOBUS,
                250
        );
        MezzoDiTrasporto mezzo3 = new MezzoDiTrasporto(
                TRAM,
                10
        );
        MezzoDiTrasporto mezzo4 = new MezzoDiTrasporto(
                AUTOBUS,
                60
        );
        MezzoDiTrasporto mezzo5 = new MezzoDiTrasporto(
                AUTOBUS,
                120
        );
      //  mezzoDAO.save(mezzo);
//          mezzoDAO.save(mezzo2);
//          mezzoDAO.save(mezzo3);
//          mezzoDAO.save(mezzo4);
//          mezzoDAO.save(mezzo5);

        //PUNTO DI VENDITA
        PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);

        //PuntoVendita puntoVendita1 = new PuntoVendita(StatoPuntoVendita.ATTIVO,TipoPuntoVendita.DISTRIBUTORE);
        PuntoVendita puntoVendita2 = new PuntoVendita(StatoPuntoVendita.FUORI_SERVIZIO,TipoPuntoVendita.DISTRIBUTORE);
        PuntoVendita puntoVendita3 = new PuntoVendita(null, TipoPuntoVendita.RIVENDITORE);
        PuntoVendita puntoVendita4 = new PuntoVendita(null,TipoPuntoVendita.RIVENDITORE);

        // puntoVenditaDAO.save(puntoVendita1);
       // puntoVenditaDAO.save(puntoVendita2);
       // puntoVenditaDAO.save(puntoVendita3);
       // puntoVenditaDAO.save(puntoVendita4);

       List <PuntoVendita> puntiVendita = puntoVenditaDAO.findAllPuntiVendita();
       PuntoVendita puntoVenditaRecuperato = puntiVendita.getFirst();


       List <MezzoDiTrasporto> mezziDiTrasporto = mezzoDAO.findAllMezzi();
       MezzoDiTrasporto mezzoRecuperato = mezziDiTrasporto.getFirst();


        //BIGLIETTO
        BigliettiDAO bigliettiDAO = new BigliettiDAO(em);

        Biglietto biglietto1 = new Biglietto("123ABC",LocalDate.now(), null,puntoVenditaRecuperato,mezzoRecuperato);
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
        // Prendo gli id di vari mezzi di trasporto registrati nel DB

        List<MezzoDiTrasporto> mezziDiTrasportoList = mezzoDAO.findAllMezzi();
        MezzoDiTrasporto mezzo2Item = mezziDiTrasportoList.get(2);
        MezzoDiTrasporto mezzo3Item = mezziDiTrasportoList.get(3);
        MezzoDiTrasporto mezzo4Item = mezziDiTrasportoList.get(4);
        MezzoDiTrasporto mezzo5Item = mezziDiTrasportoList.get(5);


        PeriodoDiServizio p2 = new PeriodoDiServizio(
                StatoServizio.IN_MANUTENZIONE,
                mezzo3Item,
                LocalDate.of(2025, 10, 16),
                null,
                "Aggiunta di nuovi posti a sedere"
        );
        PeriodoDiServizio p3 = new PeriodoDiServizio(
                StatoServizio.IN_SERVIZIO,
                mezzo2Item,
                LocalDate.of(2026, 1, 15),
                null,
                null
        );
        PeriodoDiServizio p4 = new PeriodoDiServizio(
                StatoServizio.IN_SERVIZIO,
                mezzo4Item,
                LocalDate.of(2026, 1, 6),
                null,
                null
        );
        PeriodoDiServizio p5 = new PeriodoDiServizio(
                StatoServizio.IN_MANUTENZIONE,
                mezzo5Item,
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2025, 12, 31),
                "Guasto al motore"
        );

     //   periodiDAO.save(p1);
           periodiDAO.save(p2);
           periodiDAO.save(p3);
           periodiDAO.save(p4);
           periodiDAO.save(p5);

        try {
            // Lista dei periodi di servizio dato l'id
            System.out.println("\nPeriodi di servizio: ");
            List<PeriodoDiServizio> periodiServizioLista = periodiDAO.getPeriodiDiServizio(mezzo2Item.getIdMezzi());
            if (periodiServizioLista.isEmpty()) {
                System.out.println("Non sono registrati periodi di servizio per questo mezzo.");
            } else {
                periodiServizioLista.forEach(p ->
                        System.out.println("Date di inizio servizio: " + p.getDataInizio())
                );
            }

            // Lista dei periodi di manutenzione dato l'id
            System.out.println("\nPeriodi di manutenzione: ");
            List<PeriodoDiServizio> periodiManutenzioneLista = periodiDAO.getPeriodiDiManutenzione(mezzo2Item.getIdMezzi());
            if (periodiManutenzioneLista.isEmpty()) {
                System.out.println("Non sono registrati periodi di manutenzione per questo mezzo.");
        } else {
                periodiManutenzioneLista.forEach(p ->
                        System.out.println("Causa manutenzione: " + p.getCausaManutenzione() +
                                "\nDate di inizio manutenzione: " + p.getDataInizio() +
                                " date di fine: " + p.getDataFine())
                );
            }
        } catch (NotFoundIdException ex) {
            System.out.println(ex.getMessage());
        }
}}
