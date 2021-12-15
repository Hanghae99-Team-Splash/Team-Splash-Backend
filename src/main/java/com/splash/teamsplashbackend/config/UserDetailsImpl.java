package com.splash.teamsplashbackend.config;

import com.splash.teamsplashbackend.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
public class UserDetailsImpl implements UserDetails {
    @ApiModelProperty(hidden = true)
    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }
    @ApiModelProperty(hidden = true)
    public User getUser() {
        return user;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @ApiModelProperty(hidden = true)
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isEnabled() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}