package it.polito.tdp.poweroutages.DAO;

import java.time.Duration;
import java.time.LocalDateTime;



public class PowerOutage {
	private int id;
	
	private int customers_affected;
	private LocalDateTime date_event_began;
	private LocalDateTime date_event_finished;
	private Duration event_duration;
	
	
	public PowerOutage(int id, int customers_affected, LocalDateTime date_event_began,
			LocalDateTime date_event_finished) {
		
		this.id = id;
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.event_duration = Duration.between(this.date_event_finished.toLocalTime(),
				date_event_began.toLocalTime());
	}
	
	
	public Duration getEvent_duration() {
		return event_duration;
	}


	public void setEvent_duration(Duration event_duration) {
		this.event_duration = event_duration;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomers_affected() {
		return customers_affected;
	}
	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}
	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}
	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}
	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}
	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}
	@Override
	public String toString() {
		return   date_event_began + " "+ date_event_finished + " " +customers_affected ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}
