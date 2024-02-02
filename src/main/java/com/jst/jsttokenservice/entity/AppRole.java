package com.jst.jsttokenservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String roleName;


  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<AppPermission> userPermissions;



  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority(this.getRoleName()));
    return authorities;
  }

  private Set<AppPermission> getPermissions() {
    return userPermissions;
  }
}