package com.learn.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learn.domain.conf.BaseEntity;
import com.learn.domain.serializer.UserSimpleSerializer;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "Auth")
public class UserAuthority extends BaseEntity implements GrantedAuthority {

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_User_Authority"))
    @JsonSerialize(using = UserSimpleSerializer.class)
    private User user;

    @Id
    @NotNull
    private String authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    @JsonProperty
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserAuthority))
            return false;

        UserAuthority ua = (UserAuthority) obj;
        return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
    }

    @Override
    public int hashCode() {
        return getAuthority() == null ? 0 : getAuthority().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getAuthority();
    }
}