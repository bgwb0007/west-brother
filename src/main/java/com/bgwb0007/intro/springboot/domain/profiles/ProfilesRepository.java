package com.bgwb0007.intro.springboot.domain.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfilesRepository extends JpaRepository<Profiles, Long> {

}