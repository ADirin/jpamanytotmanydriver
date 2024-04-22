import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Begin transaction
            tx.begin();

            // Create drivers
            Driver driver1 = new Driver("John");
            Driver driver2 = new Driver("Alice");

            // Create cars
            Car car1 = new Car("Toyota");
            Car car2 = new Car("Honda");

            // Associate drivers with cars
            driver1.getCars().add(car1);
            driver1.getCars().add(car2);
            driver2.getCars().add(car1);

            // Persist drivers and cars
            em.persist(driver1);
            em.persist(driver2);
            em.persist(car1);
            em.persist(car2);

            // Commit transaction
            tx.commit();

            // Query all drivers
            List<Driver> drivers = em.createQuery("SELECT d FROM Driver d", Driver.class).getResultList();
            for (Driver d : drivers) {
                System.out.println("Driver: " + d.getName());
                System.out.println("Cars:");
                for (Car c : d.getCars()) {
                    System.out.println("- " + c.getModel());
                }
                System.out.println();
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
