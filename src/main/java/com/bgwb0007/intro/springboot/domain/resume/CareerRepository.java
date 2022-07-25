package com.bgwb0007.intro.springboot.domain.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
    @Query("select c from Career c order by c.startDate desc")
    List<Career> findAllOrderByStartDateDesc();
}
