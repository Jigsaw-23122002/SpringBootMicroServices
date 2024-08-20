package com.embarkx.jobms.job;

// ORM - Object relational mapping.
// Each object in tables is POJO - Plain Old Java Object

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {

}
