package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {}