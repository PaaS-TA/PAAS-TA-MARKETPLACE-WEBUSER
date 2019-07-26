package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Role role;

    private String token;

    public enum Role implements GrantedAuthority {
        Admin, User;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);

        return roles;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return null;
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
