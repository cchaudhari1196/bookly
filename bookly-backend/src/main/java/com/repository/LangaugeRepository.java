package com.repository;

import com.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangaugeRepository extends JpaRepository<Language, Integer> {
}
