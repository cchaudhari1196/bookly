package com.repository;

import com.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> 
{
	@Query(value="select * from Vendor where v_email=?1 AND v_password=?2",nativeQuery = true)
	Vendor findByEmail(String v_email, String v_password);

}
