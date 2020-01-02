package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.LocalTableDto;
import com.mobile.freeforfun.business.dto.ReservationDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.mapper.LocalTableMapper;
import com.mobile.freeforfun.business.mapper.ReservationMapper;
import com.mobile.freeforfun.business.utils.MailService;
import com.mobile.freeforfun.persistence.enums.EReservationType;
import com.mobile.freeforfun.persistence.model.Local;
import com.mobile.freeforfun.persistence.model.LocalTable;
import com.mobile.freeforfun.persistence.model.Reservation;
import com.mobile.freeforfun.persistence.model.User;
import com.mobile.freeforfun.persistence.repo.LocalRepository;
import com.mobile.freeforfun.persistence.repo.LocalTableRepository;
import com.mobile.freeforfun.persistence.repo.ReservationRepository;
import com.mobile.freeforfun.persistence.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

	private ReservationRepository reservationRepository;
	private LocalTableRepository tableRepository;
	private UserRepository userRepository;
	private ReservationMapper reservationMapper;
	private LocalTableMapper tableMapper;
	private LocalRepository localRepository;

	public ReservationServiceImpl(
			ReservationRepository reservationRepository,
			LocalTableRepository tableRepository,
			UserRepository userRepository,
			ReservationMapper reservationMapper, LocalTableMapper tableMapper,
			LocalRepository localRepository) {
		this.reservationRepository = reservationRepository;
		this.tableRepository = tableRepository;
		this.userRepository = userRepository;
		this.reservationMapper = reservationMapper;
		this.tableMapper = tableMapper;
		this.localRepository = localRepository;
	}

	@Override public ReservationDto getReservationById(Long id) {
		Optional<Reservation> reservation = reservationRepository.findById(id);
		return reservation.map(value -> reservationMapper.toDto(value)).orElse(null);
	}

	@Override public void deleteReservation(Long id) throws BusinessException {
		Optional<Reservation> reservation = reservationRepository.findById(id);
		if(reservation.isPresent())
			reservationRepository.deleteById(id);
		else
			throw new BusinessException("Reservation with this Id does not exist. Reservation could't" +
					" be deleted!","fff-002");
	}

	@Override public ReservationDto saveReservation(ReservationDto reservationDto) throws BusinessException {
		Reservation reservation = reservationMapper.toEntityWithoutTable(reservationDto);
		LocalTable assignedTable = chooseFreeTable(reservation.getNumberOfPlaces(),
				reservation.getLocal(), reservation.getDateTimeReservation());
		if(Objects.nonNull(assignedTable)) {
			Timestamp leaveDateTime = calculateLeave(reservation.getDateTimeReservation(),
					reservation.getReservationType());
			reservation.setDateTimeLeave(leaveDateTime);
			reservation.setTable(assignedTable);
			try {
				MailService.sendMail(reservation.getUser().getEmail(),
						"FreeForFun-Confirmation reservation", createMessageForEmail(reservation));
			} catch (MessagingException e) {
				throw new BusinessException("Error when sending the email! Reservation not created!", "fff-002");
			}
			reservationRepository.save(reservation);
			return reservationMapper.toDto(reservation);
		}
		else
			throw new BusinessException("No free places for the chosen date and time! Reservation" +
					" was not created!","fff-002");
	}

	@Override public List<ReservationDto> getFutureReservationsByUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()){
			List<Reservation> reservations = reservationRepository.findAllByUser(user.get());
			List<Reservation> futureReservations = new ArrayList<>();
			for(Reservation reservation: reservations){
				if(reservation.getDateTimeReservation().after(new Timestamp(System.currentTimeMillis())))
					futureReservations.add(reservation);
			}
			return reservationMapper.toDtoList(futureReservations);
		}
		return Collections.emptyList();
	}

	@Override public List<ReservationDto> getPastReservationsByUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()){
			List<Reservation> reservations = reservationRepository.findAllByUser(user.get());
			List<Reservation> futureReservations = new ArrayList<>();
			for(Reservation reservation: reservations){
				if(reservation.getDateTimeReservation().before(new Timestamp(System.currentTimeMillis())))
					futureReservations.add(reservation);
			}
			return reservationMapper.toDtoList(futureReservations);
		}
		return Collections.emptyList();
	}

	private Timestamp calculateLeave(Timestamp arrival, EReservationType reservationType){
		long numberOfHours = 0;
		switch (reservationType){
		case ONE_HOUR:
			numberOfHours = 1;
			break;
		case TWO_THREE_HOURS:
			numberOfHours = 3;
			break;
		case FOUR_FIVE_HOURS:
			numberOfHours = 5;
			break;
		case SIX_SEVEN_HOURS:
			numberOfHours = 7;
			break;
		case MORE_THAN_7_HOURS:
			numberOfHours = 10;
			break;
		}
		return new Timestamp(arrival.getTime() + (3600000*numberOfHours));
	}

	private LocalTable chooseFreeTable(Integer numberOfPlaces, Local local, Timestamp dateTime){
		List<LocalTable> tables = tableRepository.findAllByLocalOrderByNumberOfPlacesAsc(local);
		for(LocalTable table: tables){
			if(table.getNumberOfPlaces() >= numberOfPlaces){
				if(isFreeTable(table, local, dateTime)){
					return table;
				}
			}
		}
		return null;
	}

	public List<LocalTableDto> freePlacesNow(Long localId){
		Optional<Local> local = localRepository.findById(localId);
		if(local.isPresent()) {
			List<LocalTable> tables = tableRepository.findAllByLocal(local.get());
			List<LocalTable> freeTables = new ArrayList<>();
			if (!tables.isEmpty()) {
				for (LocalTable table : tables) {
					if (isFreeTable(table, local.get(), new Timestamp(System.currentTimeMillis()))) {
						freeTables.add(table);
					}
				}
				return tableMapper.toDtoList(freeTables);
			}
		}
		return Collections.emptyList();
	}

	private boolean isFreeTable(LocalTable table, Local local, Timestamp dateTime){
		List<Reservation> reservations = reservationRepository.findAllByLocalAndTable(local, table);
		for(Reservation r: reservations){
			if((dateTime.after(r.getDateTimeReservation()) || dateTime.equals(r.getDateTimeReservation())) &&
				dateTime.before(r.getDateTimeLeave()))
				return false;
		}
		return true;
	}

	private String createMessageForEmail(Reservation reservation){
		StringBuilder mailMessage = new StringBuilder();
		mailMessage.append("Hello ");
		mailMessage.append(reservation.getUser().getFirstName());
		mailMessage.append(" ");
		mailMessage.append(reservation.getUser().getLastName());
		mailMessage.append(",");
		mailMessage.append("\n");
		mailMessage.append("\n");
		mailMessage.append("Your reservation has been successfully created with the following"
				+ " details: ");
		mailMessage.append("\n");
		mailMessage.append("\n");
		mailMessage.append("Local name: ");
		mailMessage.append(reservation.getLocal().getName());
		mailMessage.append("\n");
		mailMessage.append("Local address: ");
		mailMessage.append(reservation.getLocal().getLocation());
		mailMessage.append("\n");
		mailMessage.append("Contact number: ");
		mailMessage.append(reservation.getLocal().getMobileNumber());
		mailMessage.append("\n");
		mailMessage.append("Number of persons: ");
		mailMessage.append(reservation.getNumberOfPlaces());
		mailMessage.append("\n");
		mailMessage.append("Date and time of your reservation: ");
		mailMessage.append(reservation.getDateTimeReservation().toString().substring(0,
				reservation.getDateTimeReservation().toString().length()-5));
		mailMessage.append("\n");
		mailMessage.append("\n");
		mailMessage.append("You can cancel your reservation in case you want to change your plans. "
				+ "We hope, it won't be the case :)");
		mailMessage.append("\n");
		mailMessage.append("\n");
		mailMessage.append("Have a nice day, ");
		mailMessage.append("\n");
		mailMessage.append("\n");
		mailMessage.append("FreeForFun Team");
		return mailMessage.toString();
	}
}