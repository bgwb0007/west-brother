package com.bgwb0007.intro.springboot.domain.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    @Query("select c from Certificate c order by c.licenseDate asc")
    List<Certificate> findAllOrderByLicenseDateAsc();
}
