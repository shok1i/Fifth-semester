package com.cursework.kuroi.models;

import com.cursework.kuroi.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userBIO;

    @Column(unique = true)
    private String userNickName;

    @Column(unique = true)
    private String userEmail;

    private String password;

    private boolean active;

    private LocalDate createAt;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Art> arts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    // Производим инициализацию
    @PrePersist
    private void init() {
        createAt = LocalDate.now();
        if (userBIO == null) userBIO = "id" + id;
        if (userNickName == null) userNickName = "id" + id;
    }

    // Методы SpringSecurity
    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
