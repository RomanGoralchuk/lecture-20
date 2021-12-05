package by.itacademy.javaenterprise.goralchuk.entity.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString(callSuper=true, includeFieldNames=false)
@NoArgsConstructor
@Table(name = "prophylactic")
public class Prophylactic extends Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prophylacticIdCardNumber;
    @Column(updatable = false)
    private Date created;
    @Column(insertable = false)
    private Date updated;
    @Column(name = "history")
    private String description;

    public Prophylactic(String name, String surname, Gender gender, Date birthday,
                        int age, String password, Date created, Date updated, String description) {
        super(name, surname, gender, birthday, age, password);
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Prophylactic(String description) {
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
