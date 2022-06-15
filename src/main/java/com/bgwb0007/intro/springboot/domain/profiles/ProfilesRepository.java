package com.bgwb0007.intro.springboot.domain.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilesRepository extends JpaRepository<Profiles, Long> {
    Optional<Profiles> findByPageGubun(String pageGubun);
}
