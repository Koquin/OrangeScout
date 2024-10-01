package com.orangescout.Orange.Scout.repository;

import com.orangescout.Orange.Scout.model.Match;
import com.orangescout.Orange.Scout.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser(User user);
}
