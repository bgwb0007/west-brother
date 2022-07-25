package com.bgwb0007.intro.springboot.domain.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("select e from Education e order by e.endDate desc")
    List<Education> findAllOrderByStartDateDesc();
}
