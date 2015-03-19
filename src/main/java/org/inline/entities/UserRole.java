package org.inline.entities;

import javax.persistence.*;

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

    @SequenceGenerator(
            name = "USER_ROLE_SEQUENCE_GENERATOR",
            sequenceName = "SEQ_INLINE"
    )
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_ROLE_SEQUENCE_GENERATOR")
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