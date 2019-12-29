package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.LocalTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalTableRepository extends JpaRepository<LocalTable, Long> {
}
