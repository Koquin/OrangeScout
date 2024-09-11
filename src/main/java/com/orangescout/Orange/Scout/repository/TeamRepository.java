package com.orangescout.Orange.Scout.repository;

import com.orangescout.Orange.Scout.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
