package org.greytcoders.rent_a_bs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "This field must contain a name")
    @NotBlank(message = "This field must contain a name")
    @Size(min = 3, max = 30)
    private String name;

    @NotNull(message = "Your password can't be blank")
    @NotBlank(message = "Your password can't be blank")
    @Size(min = 8, message = "Must have at least 8 characters")
    private String password;

    @Temporal(TemporalType.DATE)
    private Date age;
    private String token;
    @Email
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(String age) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = new Date(dateFormat.parse(age).getTime());
            this.age = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
