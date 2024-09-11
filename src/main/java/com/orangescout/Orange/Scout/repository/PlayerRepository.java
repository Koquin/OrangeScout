package com.orangescout.Orange.Scout.repository;

import com.orangescout.Orange.Scout.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
