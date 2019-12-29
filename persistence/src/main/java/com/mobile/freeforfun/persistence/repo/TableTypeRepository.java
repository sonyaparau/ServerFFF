package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.TableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableTypeRepository extends JpaRepository<TableType, Long> {
}
