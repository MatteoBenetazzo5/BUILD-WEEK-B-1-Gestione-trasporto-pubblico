package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team3.dao.MezziDiTrasportoDAO;
import team3.entities.MezzoDiTrasporto;
import team3.exceptions.NotFoundException;

import java.util.UUID;

import static team3.entities.TipoMezzoDiTrasporto.AUTOBUS;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblicopu");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        MezziDiTrasportoDAO mezzoDAO = new MezziDiTrasportoDAO(em);


        MezzoDiTrasporto mezzo = new MezzoDiTrasporto(
                AUTOBUS,
                300
        );
        //mezzoDAO.save(mezzo);

        // Cerco il tipo di mezzo dato l'id
        try {
            MezzoDiTrasporto mezzoFromDB = mezzoDAO.findById(UUID.fromString("347dab0e-009e-4c87-8c92-804b25f6c5e5"));
            System.out.println(mezzoFromDB.getTipoDiMezzo());
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        //
        

    }

}
