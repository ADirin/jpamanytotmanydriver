import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @OneToMany(mappedBy = "car")
    private Set<DriverCar> driverCars = new HashSet<>();

    public String getModel() {
        return model;
    }

    public Car() {
    }

    public Car(String model) {
        this.model = model;
    }
    // Constructors, getters, and setters
}
