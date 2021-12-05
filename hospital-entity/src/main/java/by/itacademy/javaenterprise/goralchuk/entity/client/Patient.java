package by.itacademy.javaenterprise.goralchuk.entity.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
@OptimisticLocking
public class Patient extends Client {
    @Id
    @Column(name = "id")
    @SequenceGenerator( name = "patientSequence", sequenceName = "PATIENT_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "patientSequence")
    @Check(constraints = "id > 0")
    private Long patientIdCardNumber;
    @Convert(converter = LifeStatus.LifeStatusConverter.class)
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "postCode", column = @Column(name = "zipCode")),
    })
    private Address address;
    @Formula(value = "concat(id, address)")
	private String fullInformation;
    @UpdateTimestamp
    private LocalDateTime updatedBy;

    public Patient(String name, String surname, Gender gender, Date birthday, int age, String password,
                   LifeStatus lifeStatus, Address address, String fullInformation, LocalDateTime updatedBy) {
        super(name, surname, gender, birthday, age, password);
        this.lifeStatus = lifeStatus;
        this.address = address;
        this.fullInformation = fullInformation;
        this.updatedBy = updatedBy;
    }

    public Patient(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "\nPatient{" +
                "ID=" + patientIdCardNumber +
                "\nData update=" + updatedBy +
                "\nFull info=" + fullInformation +
                super.toString() +
                ", lifeStatus='" + lifeStatus.getCode() + "'" +
                "\n" + address +
                '}';
    }
}
