package com.bgwb0007.intro.springboot.domain.shoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {
}
