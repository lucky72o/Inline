package org.inline.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_roles", catalog = "inline",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "user_id" }))
public class UserRole{

    private Integer userRoleId;
    private InlineUser user;
    private String role;

    public UserRole() {
    }

    public UserRole(InlineUser user, String role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id",
            unique = true, nullable = false)
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public InlineUser getUser() {
        return this.user;
    }

    public void setUser(InlineUser user) {
        this.user = user;
    }

    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}