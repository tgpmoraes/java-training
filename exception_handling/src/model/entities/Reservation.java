package model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	
	public int duration() {
		Period difference = Period.between(checkIn, checkOut);
		return difference.getDays();
	}
	
	public String updateDates(LocalDate checkIn, LocalDate checkOut) {
		
		LocalDate currentDate = LocalDate.now();
		if(checkIn.isBefore(currentDate) || checkOut.isBefore(currentDate)) {
			return "Error in reservation: Reservation dates for update must be future dates";
		}
		if(!checkOut.isAfter(checkIn)) {
			return "Error in reservation: Check-out date must be after check-in date";
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room ");
		sb.append(roomNumber);
		sb.append(", check-in: ");
		sb.append(checkIn.format(fmt));
		sb.append(", check-out: ");
		sb.append(checkOut.format(fmt));
		sb.append(", ");
		sb.append(duration());
		sb.append(" nights");
		return sb.toString();
	}
}
