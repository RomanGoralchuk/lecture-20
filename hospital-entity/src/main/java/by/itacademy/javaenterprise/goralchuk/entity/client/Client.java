package by.itacademy.javaenterprise.goralchuk.entity.client;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Client {
    private String name;
    private String surname;
    @Convert(converter = Gender.GenderConverter.class)
    private Gender gender;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Formula(value ="SELECT DATEDIFF(CURDATE(), birthday)")
    private int age;
    @Transient
    private String password;

    @Override
    public String toString() {
        return "\nname='" + name +
                "', surname='" + surname +
                "', gender=" + gender +
                ", birthday=" + birthday +
                ", age=" + age;
    }
}
