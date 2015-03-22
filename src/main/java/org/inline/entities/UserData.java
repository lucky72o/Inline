package org.inline.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_data")
public class UserData {

    private Integer userDataId;
    private InlineUser user;
    private String name;
    private String surname;
    private String phone;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_data_id",
            unique = true, nullable = false)
    public Integer getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(Integer userDataId) {
        this.userDataId = userDataId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public InlineUser getUser() {
        return user;
    }

    public void setUser(InlineUser user) {
        this.user = user;
    }

    @Column(name = "phone", nullable = false, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "email", nullable = false, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false, length = 150)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
