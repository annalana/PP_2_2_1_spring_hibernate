package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;
    @Column
    private int series;
    @Column
    private String model;
    public Car() {}
    public Car(User user, int series, String model) {
        this.owner = user;
        this.series = series;
        this.model = model;
    }
    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }



    public User getOwner() {
        return owner;
    }

    public void setModel(String model) {
        this.model = model;
    }



    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
