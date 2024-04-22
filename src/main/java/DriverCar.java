import javax.persistence.*;

@Entity
@Table(name = "driver_cars")
public class DriverCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    // Constructors, getters, and setters
}
