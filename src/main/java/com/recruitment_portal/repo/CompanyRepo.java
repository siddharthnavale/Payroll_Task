package com.recruitment_portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment_portal.entities.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer>{

}
