package org.cloudspiretech.in.LunaCordis.repository;

import org.cloudspiretech.in.LunaCordis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
