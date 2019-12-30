package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.Local;
import com.mobile.freeforfun.persistence.model.LocalTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalTableRepository extends JpaRepository<LocalTable, Long> {
	List<LocalTable> findAllByLocal(Local local);
	List<LocalTable> findAllByLocalOrderByNumberOfPlacesAsc(Local local);
}
