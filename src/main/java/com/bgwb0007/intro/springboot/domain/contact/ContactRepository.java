package com.bgwb0007.intro.springboot.domain.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c order by c.sortOrder asc")
    List<Contact> findAllOrderBySortOrderAsc();
}
