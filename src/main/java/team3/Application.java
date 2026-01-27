package team3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import team3.dao.BigliettiDAO;
import team3.entities.Biglietto;
import team3.entities.PuntoVendita;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblicopu");

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Biglietto biglietto1 = new Biglietto("AMD123123",LocalDate.of(2026, 1, 26),LocalDate.now(), PuntoVendita )

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();


    }



}
