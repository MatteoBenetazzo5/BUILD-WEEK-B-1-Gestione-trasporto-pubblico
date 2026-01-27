package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team3.dao.MezziDiTrasportoDAO;
import team3.dao.PeriodiDiServizioDAO;
import team3.entities.MezzoDiTrasporto;
import team3.entities.PeriodoDiServizio;
import team3.entities.StatoServizio;
import team3.entities.TipoMezzoDiTrasporto;
import team3.exceptions.NotFoundException;
import team3.exceptions.NotFoundIdException;

import java.time.LocalDate;
import java.util.UUID;

import static team3.entities.TipoMezzoDiTrasporto.AUTOBUS;
import static team3.entities.TipoMezzoDiTrasporto.TRAM;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblicopu");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        // MEZZI DI TRASPORTO
        // salvo un mezzo di trasporto
        MezziDiTrasportoDAO mezzoDAO = new MezziDiTrasportoDAO(em);


        MezzoDiTrasporto mezzo = new MezzoDiTrasporto(
                TRAM,
                50
        );
        //mezzoDAO.save(mezzo);

        // Cerco il tipo di mezzo dato un id presente nel DB
        try {
            TipoMezzoDiTrasporto tipo = mezzoDAO.getTipoById(UUID.fromString("347dab0e-009e-4c87-8c92-804b25f6c5e5"));
            System.out.println("Il tipo del mezzo è: " + tipo);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // PERIODO DI SERVIZIO

        PeriodiDiServizioDAO periodiDAO = new PeriodiDiServizioDAO(em);
        // uso l'uuid di un record che ho nel DB
        UUID idMezzo = UUID.fromString("347dab0e-009e-4c87-8c92-804b25f6c5e5");
        MezzoDiTrasporto mezzo1 = mezzoDAO.findById(idMezzo);

        PeriodoDiServizio p1 = new PeriodoDiServizio(
                StatoServizio.IN_SERVIZIO,
                mezzo1,
                LocalDate.of(2026, 1, 6),
                null,
                null
        );

        //periodiDAO.save(p1);

        try {
            // Lista dei periodi di servizio dato l'id
            System.out.println("\nPeriodi di servizio: ");
            periodiDAO.getPeriodiDiServizio(idMezzo).forEach(p ->
                    System.out.println("Date di inizio servizio: " + p.getDataInizio() +
                            " date di fine: " + p.getDataFine())
            );

            // Lista dei periodi di manutenzione dato l'id
            System.out.println("\nPeriodi di manutenzione: ");
            periodiDAO.getPeriodiDiManutenzione(idMezzo).forEach(p ->
                    System.out.println("Date di inizio manutenzione: " + p.getDataInizio() +
                            " date di fine: " + p.getDataFine())
            );

        } catch (NotFoundIdException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
