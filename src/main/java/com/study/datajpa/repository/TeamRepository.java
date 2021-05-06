package com.study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.datajpa.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
