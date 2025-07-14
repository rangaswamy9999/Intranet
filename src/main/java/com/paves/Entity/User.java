// User.java
package com.paves.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles"})
@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique = true)
    private String password;

    @Column(nullable = false,unique = true)
    private long phone;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateCreated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    public void generateId(){
        if(this.userId == null){
            this.userId ="PAV"+ UUID.randomUUID().toString().replace("-","").substring(0,5).toUpperCase();
        }
        if(this.dateCreated==null)
        {
            this.dateCreated = LocalDate.now();
        }
    }

}