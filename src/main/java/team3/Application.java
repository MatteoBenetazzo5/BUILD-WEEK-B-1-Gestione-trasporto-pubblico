package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team3.dao.*;
import team3.entities.*;
import team3.exceptions.NotFoundIdException;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
                LocalDate.now().plusMonths(1),tessera, puntoVenditaRecuperato );
//        abbonamentoDAO.save(abbonamento1);
        Abbonamento abbonamento2 = new Abbonamento("LALALA7", TipoAbbonamento.SETTIMANALE, LocalDate.now(),
                LocalDate.now().plusWeeks(2),tessera1, puntoVenditaRecuperato2 );
//        abbonamentoDAO.save(abbonamento2);


        //BIGLIETTO
        BigliettiDAO bigliettiDAO = new BigliettiDAO(em);

//        Biglietto biglietto1 = new Biglietto("123ABC",LocalDate.now(), null,puntoVenditaRecuperato,mezzoRecuperato);
        //bigliettiDAO.save(biglietto1);
        Biglietto biglietto2 = new Biglietto("456DEF", LocalDate.now(), LocalDateTime.now(), puntoVenditaRecuperato, mezzoRecuperato);
//        bigliettiDAO.save(biglietto2);
        Biglietto biglietto3 = new Biglietto("789GHL", LocalDate.now(), LocalDateTime.now(), puntoVenditaRecuperato2, mezzoRecuperato2);
//        bigliettiDAO.save(biglietto3);
        Biglietto biglietto4 = new Biglietto("184THS", LocalDate.now(), LocalDateTime.now(), puntoVenditaRecuperato3, mezzoRecuperato3);
//        bigliettiDAO.save(biglietto4);


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


        boolean running = true;

        while (running) {
            System.out.println("AZIENDA DI TRASPORTO PUBBLICO");
            System.out.println("Sei un utente o un amministratore?");
            System.out.println("1 - Utente");
            System.out.println("2 - Amministratore");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

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

        em.close();
        emf.close();
        scanner.close();
    }

    // =========================
    // MENU UTENTE (come schema)
    // =========================
    private static void menuUtente() {
        boolean back = false;

        while (!back) {

            // dove ti trovi?
            // 1) distributore automatico
            // 2) rivenditore autorizzato
            // poi: cosa vuoi fare? (biglietto / abbonamento / verifica validità)

            System.out.println("UTENTE");
            System.out.println("Dove ti trovi?");
            System.out.println("1 - Distributore automatico");
            System.out.println("2 - Rivenditore autorizzato");
            System.out.println("0 - Torna indietro");
            System.out.print("Scelta: ");

            int luogo = scanner.nextInt();
            scanner.nextLine();

            switch (luogo) {
                case 1:
                    // sei al distributore automatico
                    // ora chiedi cosa vuoi fare (biglietto / abbonamento )

                    menuAzioniUtenteDistributore();
                    break;
                case 2:
                    // sei in rivenditore autorizzato
                    // ora chiedi cosa vuoi fare (biglietto / abbonamento / verifica validità)
                    menuAzioniUtenteRivenditore();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida, ritenta!");
            }
        }
    }

    // sottomenù: cosa vuoi fare? (come schema utente)
    public static void menuAzioniUtenteDistributore() {
        EntityManager em = emf.createEntityManager();
        PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(em);
        List<PuntoVendita> puntiVendita = puntoVenditaDAO.findAllPuntiVendita();
        PuntoVendita puntoVenditaRecuperato = puntiVendita.get(1); // distributore
        PuntoVendita puntoVenditaRecuperato2 = puntiVendita.get(2); // rivenditore
        MezziDiTrasportoDAO mezziDiTrasportoDAO = new MezziDiTrasportoDAO(em);
        List<MezzoDiTrasporto> mezziDiTrasporto = mezziDiTrasportoDAO.findAllMezzi();
        MezzoDiTrasporto mezzoRecuperato = mezziDiTrasporto.get(1);
        MezzoDiTrasporto mezzoRecuperato2 = mezziDiTrasporto.get(2);
        MezzoDiTrasporto mezzoRecuperato3 = mezziDiTrasporto.get(3);
        TesseraDAO tesseraDAO = new TesseraDAO(em);


        boolean back = false;

        while (!back) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1 - Fai biglietto");
            System.out.println("2 - Fai abbonamento");
            System.out.println("3 - Verifica validita abbonamento");
            System.out.println("0 - Torna indietro");
            System.out.print("Scelta: ");

            int scelta = Integer.parseInt(scanner.nextLine());
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    // schema: vuoi biglietto? -> genera biglietto dal distributore
                    Biglietto bigliettoDistributore = new Biglietto("CVL20", LocalDate.now(), null,
                            puntoVenditaRecuperato, mezzoRecuperato);
                    break;
                case 2:
                    // schema: vuoi abbonamento? -> controllo validità tessera
                    System.out.println("Inserisci il codice della tua tessera.");
                    String numTessera = scanner.nextLine();
                    Tessera tessera1 = tesseraDAO.findByCodiceTessera(numTessera);
                   boolean valTessera = tesseraDAO.isValid(numTessera);
                   if (valTessera) {
                       System.out.println("La tua tessera è valida, ti genero l'abbonamento");
                       Abbonamento abbonamentoDistributore = new Abbonamento("BNMNT", TipoAbbonamento.MENSILE,
                               LocalDate.now(), LocalDate.now().plusMonths(1), tessera1, puntoVenditaRecuperato);
                       System.out.println("\nL'abbonamento con codice: " + abbonamentoDistributore.getCodiceUnivoco() +
                               " è stato correttamente generato.");
                   } else {
                       System.out.println("Purtroppo la tua tessera è scaduta, creazione di una nuova tessera in corso.");
                       Tessera nuovaTessera = new Tessera("TSSR", LocalDate.now(), LocalDate.now().plusYears(1),
                              tessera1.getUtente() );
                       System.out.println("\nLa tua nuova tessera con codice: " + nuovaTessera.getCodiceTessera() +
                               " è stata correttamente creata.");
                       // rimandare al case 2
                   }
                    break;
                case 3:
                    // schema: inserisci numero tessera -> valido/non valido
                    // se non valido: vuoi rinnovarla? si/no
                    // se valido: vuoi abbonamento? -> genera abbonamento
                    // ...
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida, ritenta!");
            }
        }
    }

    // =================================
    // MENU AMMINISTRATORE (come schema)
    // =================================
    private static void menuAmministratore() {
        boolean back = false;

        while (!back) {

            // admin: cosa vuoi visualizzare?
            // biglietti / tessere emesse / mezzi / extra distributori / aggiungere cose
            // + tua voce già corretta: verifica abbonamento per tessera

            System.out.println("ADMIN");
            System.out.println("Cosa vuoi visualizzare?");
            System.out.println("1 - Biglietti");
            System.out.println("2 - Verifica abbonamento per tessera");
            System.out.println("3 - Tessere emesse");
            System.out.println("4 - Mezzi");
            System.out.println("5 - EXTRA: lista distributori attivi/non attivi");
            System.out.println("6 - Aggiungere cose");
            System.out.println("0 - Torna indietro");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    // apre sottomenù biglietti (come schema admin)
                    // ...
                    break;
                case 2:
                    // già collegato alla tua parte (DAO abbonamento)
                    verificaAbbonamentoAdmin();
                    break;
                case 3:
                    // schema: tessere emesse
                    // ...
                    break;
                case 4:
                    // apre sottomenù mezzi (periodi servizio/manutenzione, percorrenze/tempo medio)
                    // ...
                    break;
                case 5:
                    // schema extra: lista distributori attivi/non attivi
                    // ...
                    break;
                case 6:
                    // schema: aggiungere cose (creazioni/aggiunte varie)
                    // ...
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida, ritenta!");
            }
        }
    }

    // ======================
    // METODI UTENTE (bozza)
    // ======================

    private static void metodoUtente1() {
        // ...
    }

    private static void metodoUtente2() {
        // ...
    }

    private static void metodoUtente3() {
        // ...
    }

    // ==============================
    // METODI AMMINISTRATORE (bozza)
    // ==============================

    private static void metodoAdmin1() {
        // ...
    }

    // ============================
    // SOLO PER LA TUA PARTE (DAO)
    // ============================
    private static void verificaAbbonamentoAdmin() {

        // admin: verifica rapida abbonamento per codice tessera (come consegna)
        // chiede codice tessera
        // controlla se valido in base alla data odierna
        // stampa valido / non valido

        System.out.println("Inserisci codice tessera:");
        String codiceTessera = scanner.nextLine();

        LocalDate oggi = LocalDate.now();

        boolean valido = abbonamentoDAO.isAbbonamentoValido(codiceTessera, oggi);

        if (valido) {
            System.out.println("Abbonamento VALIDO");
        } else {
            System.out.println("Abbonamento NON valido o assente");
        }
    }

    private static void metodoAdmin3() {
        // ...
    }

    private static void metodoAdmin4() {
        // ...
    }

    private static void metodoAdmin5() {
        // ...
    }

    private static void metodoAdmin6() {
        // ...
    }

    }
}
