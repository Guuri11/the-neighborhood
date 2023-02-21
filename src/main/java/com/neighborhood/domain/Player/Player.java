package com.neighborhood.domain.Player;

import com.neighborhood.domain.Chat;
import com.neighborhood.domain.PlayerCareerHistory;
import com.neighborhood.domain.StreetGame.StreetGame;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
public class Player implements UserDetails {

  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
  @NotEmpty(message = "Name can't be empty")
  @Column(nullable = false)
  private String name;
  @Email(message = "Email format wrong")
  @NotEmpty(message = "Email can't be empty")
  @Column(unique = true)
  private String email;
  private String nickname;
  private Byte height;
  private Byte weight;
  @PastOrPresent(message = "Birthday not valid")
  private LocalDateTime birthdate;
  @Min(message = "Min level is 0", value = 0)
  private Long level;
  @Min(message = "Min experience is 0", value = 0)
  private Long experience;
  @Enumerated(EnumType.STRING)
  private Position position;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  @Enumerated(EnumType.STRING)
  private Archetype archetype;
  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToMany
  @JoinTable(name = "player_street_games",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "street_games_id"))
  private Set<StreetGame> streetGames = new LinkedHashSet<>();

  @OneToMany(mappedBy = "player")
  private List<PlayerCareerHistory> playerCareerHistories = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "player_chats",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "chats_id"))
  private Set<Chat> chats = new LinkedHashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {

    return id.toString();
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

    return true;
  }
}
