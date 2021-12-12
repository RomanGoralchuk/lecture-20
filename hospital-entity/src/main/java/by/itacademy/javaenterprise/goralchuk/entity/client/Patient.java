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
    @AttributeOverrides({
            @AttributeOverride(name = "postCode", column = @Column(name = "zipCode")),
    })
    @Embedded
    private Address address;
    @Formula(value = "concat(name, surname)")
	private String fullInformation;
    @UpdateTimestamp
    private LocalDateTime updatedBy;

    @Override
    public String toString() {
        return "\n<br>Patient{" +
                "ID=" + patientIdCardNumber +
                "\n<br>Data update=" + updatedBy +
                "\n<br>Full info=" + fullInformation +
                super.toString() +
                ", lifeStatus='" + lifeStatus.getCode() + "'" +
                "\n<br>" + address +
                '}';
    }
}
