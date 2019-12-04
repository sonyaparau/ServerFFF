package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.enums.ELocalType;
import com.mobile.freeforfun.persistence.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

	List<Local> findByName(String name);
	Optional<Local> findById(Long id);
	List<Local> findAllByType (ELocalType localType);
}
