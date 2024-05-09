package com.akosoravecz.accountmanager.repository.user;

import com.akosoravecz.accountmanager.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Optional<Collection<Role>> findAllByName(String name);
}
