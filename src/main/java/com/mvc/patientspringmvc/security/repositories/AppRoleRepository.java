package com.mvc.patientspringmvc.security.repositories;

import com.mvc.patientspringmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}