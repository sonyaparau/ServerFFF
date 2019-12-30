package com.mobile.freeforfun.persistence.repo;

import com.mobile.freeforfun.persistence.model.Local;
import com.mobile.freeforfun.persistence.model.LocalTable;
import com.mobile.freeforfun.persistence.model.Reservation;
import com.mobile.freeforfun.persistence.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Optional<Reservation> findById(Long id);
	List<Reservation> findAllByUser(User user);
	List<Reservation> findAllByLocal(Local local);
	List<Reservation> findAllByLocalAndTable(Local local, LocalTable table);
	void deleteById(@NonNull Long id);
}
