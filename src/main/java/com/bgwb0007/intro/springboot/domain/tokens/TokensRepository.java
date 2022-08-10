package com.bgwb0007.intro.springboot.domain.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<Tokens, Long> {

    Optional<Tokens> findByName(String name);
}
