package cl.copeuch.challenge.services.user.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cl.copeuch.challenge.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class UserDetailsImpl implements UserDetails {

    private UUID uuid;

    private String username;

    @JsonIgnore
    private String password;

    private String firstAuthorities;

    private List<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(UUID uuid, String username, String password,
                           List<? extends GrantedAuthority> authorities) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UserDetailsImpl(
                user.getUuid(),
                user.getUsername(),
                user.getPasswd(),
                authorities);
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFirstAuthorities() {
        firstAuthorities = authorities.get(0).getAuthority().toString();
        return firstAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UUID getUuid() {
        return uuid;
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
