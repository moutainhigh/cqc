package com.cqc.security.bean;

import com.cqc.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/
public class PortalUserDetails implements UserDetails {

    private User user;

    public PortalUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }


    @Override
    public boolean isAccountNonExpired() {
        // 如果账户有关闭时间 并且关闭时间在当前时间之前，说明账号已经被关闭
        return user.getCloseTime() != null && user.getCloseTime().before(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }

    public User getUser() {
        return user;
    }
}
