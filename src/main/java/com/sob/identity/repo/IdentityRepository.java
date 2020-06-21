package com.sob.identity.repo;

import com.sob.identity.repo.models.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, String> {
    Identity findUserByEmailAndPassword(String email, String password);
    Identity findUserByEmail(String email);
}
