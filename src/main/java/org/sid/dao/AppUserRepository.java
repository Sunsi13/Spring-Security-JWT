package org.sid.dao;

import org.sid.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public UserDetails findByUsername(String username);
}
