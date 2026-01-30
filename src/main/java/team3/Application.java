package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import team3.dao.*;
import team3.entities.*;
import team3.exceptions.NotFoundIdException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static team3.entities.TipoMezzoDiTrasporto.AUTOBUS;
import static team3.entities.TipoMezzoDiTrasporto.TRAM;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblicopu");
    private static Scanner scanner = new Scanner(System.in);

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
        PuntoVendita puntoVendita2 = new PuntoVendita(StatoPuntoVendita.FUORI_SERVIZIO, TipoPuntoVendita.DISTRIBUTORE);
        PuntoVendita puntoVendita3 = new PuntoVendita(null, TipoPuntoVendita.RIVENDITORE);
        PuntoVendita puntoVendita4 = new PuntoVendita(null, TipoPuntoVendita.RIVENDITORE);

        // puntoVenditaDAO.save(puntoVendita1);
        // puntoVenditaDAO.save(puntoVendita2);
        // puntoVenditaDAO.save(puntoVendita3);
        // puntoVenditaDAO.save(puntoVendita4);

        List<PuntoVendita> puntiVendita = puntoVenditaDAO.findAllPuntiVendita();
        PuntoVendita puntoVenditaRecuperato = puntiVendita.get(1);
        PuntoVendita puntoVenditaRecuperato2 = puntiVendita.get(2);
        PuntoVendita puntoVenditaRecuperato3 = puntiVendita.get(3);


        List<MezzoDiTrasporto> mezziDiTrasporto = mezzoDAO.findAllMezzi();
        MezzoDiTrasporto mezzoRecuperato = mezziDiTrasporto.get(1);
        MezzoDiTrasporto mezzoRecuperato2 = mezziDiTrasporto.get(2);
        MezzoDiTrasporto mezzoRecuperato3 = mezziDiTrasporto.get(3);

        // TESSERE PER ABBONAMENTI
        List<Tessera> tesseraList = tesseraDAO.findAllTessere();
        Tessera tessera = tesseraList.get(0);
        Tessera tessera1 = tesseraList.get(1);

        // ABBONAMENTI
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        Abbonamento abbonamento1 = new Abbonamento("CIAO3", TipoAbbonamento.MENSILE, LocalDate.now(),
                LocalDate.now().plusMonths(1), tessera, puntoVenditaRecuperato);
//        abbonamentoDAO.save(abbonamento1);
        Abbonamento abbonamento2 = new Abbonamento("LALALA7", TipoAbbonamento.SETTIMANALE, LocalDate.now(),
                LocalDate.now().plusWeeks(2), tessera1, puntoVenditaRecuperato2);
//        abbonamentoDAO.save(abbonamento2);


        //BIGLIETTO
        BigliettiDAO bigliettiDAO = new BigliettiDAO(em);

      Biglietto biglietto1 = new Biglietto("123ABC",LocalDate.now(), null,puntoVenditaRecuperato,mezzoRecuperato);
       // bigliettiDAO.save(biglietto1);
        Biglietto biglietto2 = new Biglietto("456DEF", LocalDate.now(), LocalDate.now().plusMonths(3), puntoVenditaRecuperato, mezzoRecuperato);
       // bigliettiDAO.save(biglietto2);
        Biglietto biglietto3 = new Biglietto("789GHL", LocalDate.now(), LocalDate.now().plusMonths(2), puntoVenditaRecuperato2, mezzoRecuperato2);
       // bigliettiDAO.save(biglietto3);
        Biglietto biglietto4 = new Biglietto("184THS", LocalDate.now(), LocalDate.now().plusMonths(1), puntoVenditaRecuperato3, mezzoRecuperato3);
       // bigliettiDAO.save(biglietto4);




        // Cerco il tipo di mezzo dato un id presente nel DB
//        try {
//            TipoMezzoDiTrasporto tipo = mezzoDAO.getTipoById(UUID.fromString("690ec275-f589-4b7d-bba8-7efe9bd7737f"));
//            System.out.println("Il tipo del mezzo è: " + tipo);
//        } catch (NotFoundIdException ex) {
//            System.out.println(ex.getMessage());
//        }

        // PERIODO DI SERVIZIO

        PeriodiDiServizioDAO periodiDAO = new PeriodiDiServizioDAO(em);
        // Prendo gli id di vari mezzi di trasporto registrati nel DB

        List<MezzoDiTrasporto> mezziDiTrasportoList = mezzoDAO.findAllMezzi();
        MezzoDiTrasporto mezzo2Item = mezziDiTrasportoList.get(1);
        MezzoDiTrasporto mezzo3Item = mezziDiTrasportoList.get(2);
        MezzoDiTrasporto mezzo4Item = mezziDiTrasportoList.get(3);
        MezzoDiTrasporto mezzo5Item = mezziDiTrasportoList.get(4);


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
//           periodiDAO.save(p2);
//           periodiDAO.save(p3);
//           periodiDAO.save(p4);
//           periodiDAO.save(p5);

//        try {
//            // Lista dei periodi di servizio dato l'id
//            System.out.println("\nPeriodi di servizio: ");
//            List<PeriodoDiServizio> periodiServizioLista = periodiDAO.getPeriodiDiServizio(mezzo2Item.getIdMezzi());
//            if (periodiServizioLista.isEmpty()) {
//                System.out.println("Non sono registrati periodi di servizio per questo mezzo.");
//            } else {
//                periodiServizioLista.forEach(p ->
//                        System.out.println("Date di inizio servizio: " + p.getDataInizio())
//                );
//            }

        // Lista dei periodi di manutenzione dato l'id
//            System.out.println("\nPeriodi di manutenzione: ");
//            List<PeriodoDiServizio> periodiManutenzioneLista = periodiDAO.getPeriodiDiManutenzione(mezzo2Item.getIdMezzi());
//            if (periodiManutenzioneLista.isEmpty()) {
//                System.out.println("Non sono registrati periodi di manutenzione per questo mezzo.");
//            } else {
//                periodiManutenzioneLista.forEach(p ->
//                        System.out.println("Causa manutenzione: " + p.getCausaManutenzione() +
//                                "\nDate di inizio manutenzione: " + p.getDataInizio() +
//                                " date di fine: " + p.getDataFine())
//                );
//            }
//        } catch (NotFoundIdException ex) {
//            System.out.println(ex.getMessage());
//        }

        // TRATTE E PERCORRENZE


        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);

////  CREO UNA TRATTA + UNA PRIMA PERCORRENZA
//        percorrenzaDAO.createTrattaAndPercorrenza(
//                mezzo2Item,
//                "Stazione Nord",
//                "colosseo",
//                37,   // tempo previsto
//                42    // tempo effettivo
//        );

////  RECUPERO LA TRATTA APPENA CREATA
//        Tratta trattaCreata = percorrenzaDAO.findAllPercorrenze()
//                .getLast()               // ultima percorrenza inserita
//                .getTratta();

//  AGGIUNGO ALTRE PERCORRENZE SULLA STESSA TRATTA
//        percorrenzaDAO.createAndSave(mezzoRecuperato, trattaCreata.getId_tratta(), 38);
//        percorrenzaDAO.createAndSave(mezzoRecuperato, trattaCreata.getId_tratta(), 36);
//        percorrenzaDAO.createAndSave(mezzoRecuperato, trattaCreata.getId_tratta(), 42);

//  STAMPO TUTTE LE PERCORRENZE
//        System.out.println("\n--- LISTA PERCORRENZE ---");
//        percorrenzaDAO.findAllPercorrenze().forEach(p ->
//                System.out.println(
//                        "Percorrenza " + p.getId_percorrenze()
//                                + " | Mezzo: " + p.getMezzo().getTipoDiMezzo()
//                                + " | Tratta: " + p.getTratta().getZona_partenza()
//                                + " - " + p.getTratta().getCapolinea()
//                                + " | Minuti effettivi: " + p.getMinuti_effettivi()
//                )
//        );

//  CALCOLO TEMPO MEDIO EFFETTIVO
//        double media = percorrenzaDAO.getTempoMedioEffettivo(mezzoRecuperato, trattaCreata.getId_tratta());
//        System.out.println("\nTempo medio effettivo sulla tratta: " + (int) Math.round(media)+ " minuti");


        em.close();

        // avvio menu
        menuPrincipale();

        // chiusure finali
        scanner.close();
        emf.close();


    }

    private static void menuPrincipale() {
        boolean running = true;

        while (running) {
            System.out.println("AZIENDA DI TRASPORTO PUBBLICO");
            System.out.println("Sei un utente o un amministratore?");
            System.out.println("1 - Utente");
            System.out.println("2 - Amministratore");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");

            String input = scanner.nextLine();
            int scelta;
            try {
                scelta = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Scelta non valida, ritenta.");
                continue;
            }

            switch (scelta) {
                case 1: // utente
                    menuUtente();
                    break;
                case 2: // amministratore
                    menuAmministratore();
                    break;
                case 0: // esci
                    running = false;
                    System.out.println("Uscita dal sistema, arrivederci e a presto!");
                    break;
                default:
                    System.out.println("Scelta non valida, ritenta.");
            }
        }
    }

    /////////////////////////////
    // MENU UTENTE
    /// /////////////////////////
    private static void menuUtente() {
        boolean back = false;

        while (!back) {
            System.out.println("UTENTE");
            System.out.println("Dove ti trovi?");
            System.out.println("1 - Distributore automatico");
            System.out.println("2 - Rivenditore autorizzato");
            System.out.println("0 - Torna indietro");
            System.out.print("Scelta: ");

            String input = scanner.nextLine();
            int luogo;
            try {
                luogo = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Scelta non valida, riprova!");
                continue;
            }

            switch (luogo) {
                case 1: // distributore automatico
                    menuAzioniUtenteDistributore();
                    break;
                case 2: // rivenditore autorizzato
                    menuAzioniUtenteRivenditore();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova!");
                    break;
            }
        }
    }

    // case 1 dello switch precedente -> luogo -> distributore automatico
    public static void menuAzioniUtenteDistributore() {
        EntityManager em = emf.createEntityManager();
        try {
            PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);
            List<PuntoVendita> puntiVendita = puntoVenditaDAO.findAllPuntiVendita();
            PuntoVendita puntoVenditaRecuperato = puntiVendita.get(1); // distributore

            MezziDiTrasportoDAO mezziDiTrasportoDAO = new MezziDiTrasportoDAO(em);
            List<MezzoDiTrasporto> mezziDiTrasporto = mezziDiTrasportoDAO.findAllMezzi();
            MezzoDiTrasporto mezzoRecuperato = mezziDiTrasporto.get(1);

            TesseraDAO tesseraDAO = new TesseraDAO(em);

            boolean back = false;

            while (!back) {
                System.out.println("Cosa vuoi fare?");
                System.out.println("1 - Fai biglietto");
                System.out.println("2 - Fai abbonamento");
                System.out.println("0 - Torna indietro");
                System.out.print("Scelta: ");

                String input = scanner.nextLine();
                int scelta;
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Scelta non valida, ritenta!");
                    continue;
                }

                switch (scelta) {
                    case 1:
                        Biglietto bigliettoDistributore = new Biglietto("CVL20", LocalDate.now(), null,
                                puntoVenditaRecuperato, mezzoRecuperato);
                        System.out.println("Il tuo biglietto con codice: " + bigliettoDistributore.getCodiceUnivoco() + " è stato generato con successo");
                        System.out.println("Reindirizzamento al menu principale.");
                        return;

                    case 2:
                        System.out.println("Inserisci il codice della tua tessera.");
                        String numTessera = scanner.nextLine();

                        Tessera tessera1 = tesseraDAO.findByCodiceTessera(numTessera);
                        boolean valTessera = tesseraDAO.isValid(numTessera);

                        if (valTessera) {
                            System.out.println("La tua tessera è valida, ti genero l'abbonamento");
                            Abbonamento abbonamentoDistributore = new Abbonamento("BNMNT03", TipoAbbonamento.MENSILE,
                                    LocalDate.now(), LocalDate.now().plusMonths(1), tessera1, puntoVenditaRecuperato);
                            System.out.println("\nL'abbonamento con codice: " + abbonamentoDistributore.getCodiceUnivoco() +
                                    " è stato correttamente generato.");
                            System.out.println("Reindirizzamento al menu principale.");
                            return;

                        } else {
                            System.out.println("Purtroppo la tua tessera è scaduta, creazione di una nuova tessera in corso.");
                            Tessera nuovaTessera = new Tessera("TSSR", LocalDate.now(), LocalDate.now().plusYears(1),
                                    tessera1.getUtente());
                            System.out.println("\nLa tua nuova tessera con codice: " + nuovaTessera.getCodiceTessera() +
                                    " Ã¨ stata correttamente creata.");
                            System.out.println("Reindirizzamento al menu principale.");
                            return;
                        }

                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Scelta non valida, ritenta!");
                }
            }
        } finally {
            em.close();
        }
    }

    // case 2 dello switch precedente -> luogo -> rivenditore autorizzato
    public static void menuAzioniUtenteRivenditore() {
        EntityManager em = emf.createEntityManager();
        try {
            PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);
            List<PuntoVendita> puntiVendita = puntoVenditaDAO.findAllPuntiVendita();
            PuntoVendita puntoVenditaRecuperato2 = puntiVendita.get(2); // rivenditore

            MezziDiTrasportoDAO mezziDiTrasportoDAO = new MezziDiTrasportoDAO(em);
            List<MezzoDiTrasporto> mezziDiTrasporto = mezziDiTrasportoDAO.findAllMezzi();
            MezzoDiTrasporto mezzoRecuperato2 = mezziDiTrasporto.get(2);

            TesseraDAO tesseraDAO = new TesseraDAO(em);
            BigliettiDAO bigliettiDAO = new BigliettiDAO(em);
            AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);

            boolean back = false;

            while (!back) {
                System.out.println("Cosa vuoi fare?");
                System.out.println("1 - Fai biglietto");
                System.out.println("2 - Fai abbonamento");
                System.out.println("0 - Torna indietro");
                System.out.print("Scelta: ");

                String input = scanner.nextLine();
                int scelta;
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Scelta non valida, ritenta!");
                    continue;
                }

                switch (scelta) {
                    case 1:
                        Biglietto bigliettoDistributore = new Biglietto("CVL20", LocalDate.now(), null,
                                puntoVenditaRecuperato2, mezzoRecuperato2);
                        System.out.println("Il tuo biglietto con codice: " + bigliettoDistributore.getCodiceUnivoco() + " è stato generato con successo");
                        System.out.println("Reindirizzamento al menu principale.");
                        return;

                    case 2:
                        System.out.println("Inserisci il codice della tua tessera.");
                        String numTessera = scanner.nextLine();

                        Tessera tessera1 = tesseraDAO.findByCodiceTessera(numTessera);
                        boolean valTessera = tesseraDAO.isValid(numTessera);

                        if (valTessera) {
                            System.out.println("La tua tessera è valida, ti genero l'abbonamento");
                            Abbonamento abbonamentoDistributore = new Abbonamento("BNMNT01", TipoAbbonamento.MENSILE,
                                    LocalDate.now(), LocalDate.now().plusMonths(1), tessera1, puntoVenditaRecuperato2);
                            System.out.println("\nL'abbonamento con codice: " + abbonamentoDistributore.getCodiceUnivoco() +
                                    " L'abbonamento è stato correttamente generato.");
                            System.out.println("Reindirizzamento al menu principale.");
                            return;

                        } else {
                            System.out.println("Purtroppo la tua tessera è scaduta oppure non esiste.");
                            System.out.println("Reindirizzamento al menu principale.");
                            return;
                        }

                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Scelta non valida, ritenta!");
                }
            }
        } finally {
            em.close();
        }
    }

    ////////////////////////////////////
    // MENU AMMINISTRATORE
    ////////////////////////////////////
    private static void menuAmministratore() {
        boolean back = false;

        while (!back) {

            System.out.println("ADMIN");
            System.out.println("Su cosa vuoi maggiori informazioni?");
            System.out.println("1 - Biglietti");
            System.out.println("2 - Abbonamenti");
            System.out.println("3 - Mezzi");
            System.out.println("4 - Aggiungi nuova tratta");
            System.out.println("0 - Torna indietro");
            System.out.print("Scelta: ");

            String input = scanner.nextLine();
            int scelta;
            try {
                scelta = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Scelta non valida, riprova!");
                continue;
            }

            switch (scelta) {
                case 1: // Biglietti
                    menuBiglietti();
                    break;
                case 2: // Abbonamenti
                    menuAbbonamenti();
                    break;
                case 3:
                    menuMezzi();
                    break;
                case 4:
                    menuTratta();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida, riprova!");
            }
        }
    }

    private static void menuBiglietti() {
        EntityManager em = emf.createEntityManager();
        try {
            PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);
            BigliettiDAO bigliettiDAO = new BigliettiDAO(em);

            boolean back = false;

            while (!back) {
                System.out.println("1 - Biglietti emessi per punto vendita");
                System.out.println("2 - Biglietti emessi in un dato periodo di tempo");
                System.out.println("3 - Biglietti vidimati su un determinato mezzo");
                System.out.println("4 - Biglietti vidimati in un dato periodo di tempo");
                System.out.println("0 - Esci");

                String input = scanner.nextLine();
                int scelta;
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Scelta non valida.");
                    continue;
                }

                switch (scelta) {
                    case 0:
                        back = true;
                        break;

                    case 1:
                        System.out.println("Inserisci l'ID del punto vendita di cui vuoi visualizzare il numero di biglietti emessi.");
                        try {
                            UUID idPuntoEmissione = UUID.fromString(scanner.nextLine());
                            long numeroB = puntoVenditaDAO.countBigliettiPerPuntoEmissione(idPuntoEmissione);
                            System.out.println("Ecco il numero di biglietti emessi per il punto vendita: " + numeroB);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Scelta non valida.");
                        }
                        break;
                    case 2:
                        System.out.println("Numero di biglietti emessi in un determinato periodo di tempo");
                        System.out.println("Inserisci la data di inizio, nel formato [YYYY-MM-DD]");
                        LocalDate inizio = LocalDate.parse(scanner.nextLine());
                        System.out.println("Inserisci la data di fine: ");
                        LocalDate fine = LocalDate.parse(scanner.nextLine());
                        long biglietti = bigliettiDAO.findBigliettiEmessiPeriodo(inizio, fine);
                        System.out.println("Il numero di biglietti emessi nel periodo è: " + biglietti);
                        return;
                    case 3:
                        System.out.println("Inserisci l'id del mezzo di cui vuoi vedere il numero di biglietti vidimati.");
                        try {
                            UUID idMezzoEmissione = UUID.fromString(scanner.nextLine());
                            long numeroM = bigliettiDAO.findBigliettiVidimatiPerMezzo(idMezzoEmissione);
                            System.out.println("Ecco il numero di biglietti emessi per il mezzo di trasporto scelto: " + numeroM);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Scelta non valida.");
                        }
                        break;
                    case 4:
                        System.out.println("Numero di biglietti vidimati in un determinato periodo di tempo");
                        System.out.println("Inserisci la data di inizio, nel formato [YYYY-MM-DD]");
                        LocalDate inizioV = LocalDate.parse(scanner.nextLine());
                        System.out.println("Inserisci la data di fine: ");
                        LocalDate fineV = LocalDate.parse(scanner.nextLine());
                        long bigliettiV = bigliettiDAO.countBigliettiVidimatiPeriodo(inizioV, fineV);
                        System.out.println("Il numero di biglietti vidimati in questo periodo periodo è: " + bigliettiV);
                        return;
                    default:
                        System.out.println("Scelta non valida.");
                }
            }
        } finally {
            em.close();
        }
    }

    private static void menuAbbonamenti() {
        EntityManager em = emf.createEntityManager();
        try {
            PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);
            AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);

            boolean back = false;

            while (!back) {
                System.out.println("1 - Abbonamenti emessi per punto vendita");
                System.out.println("2 - Abbonamenti emessi in un dato periodo di tempo");
                System.out.println("0 - Esci");

                String input = scanner.nextLine();
                int scelta;
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Scelta non valida.");
                    continue;
                }

                switch (scelta) {
                    case 0:
                        back = true;
                        break;

                    case 1:
                        System.out.println("Inserisci l'ID del punto di vendita di cui vuoi visualizzare il numero di abbonamenti emessi.");
                        try {
                            UUID idPuntoEmissione = UUID.fromString(scanner.nextLine());
                            long numeroA = puntoVenditaDAO.countAbbonamentiPerPuntoEmissione(idPuntoEmissione);
                            System.out.println("Ecco il numero di biglietti emessi per il punto vendita: " + numeroA);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Scelta non valida.");
                        }
                        break;
                    case 2:
                        System.out.println("Numero di abbonamenti emessi in un determinato periodo di tempo");
                        System.out.println("Inserisci la data di inizio, nel formato [YYYY-MM-DD]");
                        LocalDate inizio = LocalDate.parse(scanner.nextLine());
                        System.out.println("Inserisci la data di fine: ");
                        LocalDate fine = LocalDate.parse(scanner.nextLine());
                        long abbonamenti = abbonamentoDAO.abbonamentiEmessiPeriodo(inizio, fine);
                        System.out.println("Il numero di abbonamenti emessi nel periodo è: " + abbonamenti);
                        return;

                    default:
                        System.out.println("Scelta non valida.");
                }
            }
        } finally {
            em.close();
        }
    }

    private static void menuMezzi() {
        EntityManager em = emf.createEntityManager();
        try {
            MezziDiTrasportoDAO mezziDiTrasportoDAO = new MezziDiTrasportoDAO(em);
            PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
            PeriodiDiServizioDAO periodiDiServizioDAO = new PeriodiDiServizioDAO(em);
            TrattaDAO trattaDAO = new TrattaDAO(em);

            boolean back = false;

            while (!back) {
                System.out.println("1 - Periodi di servizio di un mezzo");
                System.out.println("2 - Periodi di manutenzione di un mezzo");
                System.out.println("3 - Numero di volte che un mezzo ha percorso una determinata tratta + tempo medio effettivo");
                System.out.println("0 - Esci");

                String input = scanner.nextLine();
                int scelta;
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Scelta non valida.");
                    continue;
                }

                switch (scelta) {
                    case 0:
                        back = true;
                        break;

                    case 1:
                        System.out.println("Inserisci l'ID del mezzo di cui vuoi visualizzare i periodi di servizio.");
                        try {
                            UUID idPeriodoMezzo = UUID.fromString(scanner.nextLine());
                            List<PeriodoDiServizio> periodiServizioLista = periodiDiServizioDAO.getPeriodiDiServizio(idPeriodoMezzo);
                          if (periodiServizioLista.isEmpty()) {
                              System.out.println("Non sono registrati periodi di servizio per questo mezzo.");
                           } else {
                               periodiServizioLista.forEach(p ->
                               System.out.println("Date di inizio servizio: " + p.getDataInizio()));


                                   }
                        } catch (NotFoundIdException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Inserisci l'ID del mezzo di cui vuoi visualizzare i periodi di manutenzione.");
                        try {
                            UUID idPeriodoManutenzione = UUID.fromString(scanner.nextLine());
                             System.out.println("\nPeriodi di manutenzione: ");
          List<PeriodoDiServizio> periodiManutenzioneLista = periodiDiServizioDAO.getPeriodiDiManutenzione(idPeriodoManutenzione);
            if (periodiManutenzioneLista.isEmpty()) {
               System.out.println("Non sono registrati periodi di manutenzione per questo mezzo.");
            } else {
                periodiManutenzioneLista.forEach(p ->
                {
                    if (p.getDataFine() == null) {
                        System.out.println("Causa manutenzione: " + p.getCausaManutenzione() +
                                "Date di inizio manutenzione: " + p.getDataInizio());
                    } else

                        System.out.println("Causa manutenzione: " + p.getCausaManutenzione() +
                                "\nDate di inizio manutenzione: " + p.getDataInizio() +
                                " date di fine: " + p.getDataFine());
                });
            }
        } catch (NotFoundIdException ex) {
            System.out.println(ex.getMessage());
        }
                        break;

                    case 3:
                        System.out.println("Inserisci l'ID del mezzo.");
                        try {
                            UUID idMezzo = UUID.fromString(scanner.nextLine());
                            MezzoDiTrasporto mezzomezzo = mezziDiTrasportoDAO.findById(idMezzo);
                            System.out.println("Inserisci l'ID della tratta.");
                            UUID idTratta = UUID.fromString(scanner.nextLine());
                            long trattaMezzo = (long) percorrenzaDAO.getTempoMedioEffettivo(mezzomezzo, idTratta);
                            System.out.println("Il tempo medio di percorrenza è: " + trattaMezzo);
                            long numeroPercorrenze = percorrenzaDAO.countPercorrenze(mezzomezzo, idTratta);
                            System.out.println("Il mezzo ha percorso la tratta " + numeroPercorrenze + " volte.");

                        } catch (IllegalArgumentException ex) {
                            System.out.println("Scelta non valida.");
                        }
                        break;

                    default:
                        System.out.println("Scelta non valida.");
                }
            }
        } finally {
            em.close();
        }
    }

    private static void menuTratta() {
        EntityManager em = emf.createEntityManager();
        try {
            TrattaDAO trattaDAO = new TrattaDAO(em);

            boolean back = false;

            while (!back) {
                System.out.println("1 - Crea una nuova tratta");
                System.out.println("0 - Esci");

                String input = scanner.nextLine();
                int scelta;
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Scelta non valida.");
                    continue;
                }

                switch (scelta) {
                    case 0:
                        back = true;
                        break;

                    case 1:
                        System.out.println("Inserisci la zona di partenza");
                        String partenza = scanner.nextLine();
                        System.out.println("Inserisci la zona di arrivo");
                        String arrivo = scanner.nextLine();
                        System.out.println("Inserisci il tempo previsto per la tratta (in minuti).");
                        int tempo = Integer.parseInt(scanner.nextLine());

                        Tratta trattaNuova = new Tratta(partenza, arrivo, tempo);
                        System.out.println("La seguente tratta è stata creata correttamente: " + trattaNuova);
                        break;

                    default:
                        System.out.println("Scelta non valida.");
                }
            }
        } finally {
            em.close();
        }
    }
}
