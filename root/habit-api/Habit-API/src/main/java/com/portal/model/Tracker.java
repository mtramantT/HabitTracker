package com.portal.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.micrometer.core.lang.NonNull;
import lombok.Data;

@Data
@Entity
@Table(name="tracker")
public class Tracker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id")
	private long id;
	
	@NonNull
	@Column(name="habit")
	private String habit;
	
	@Column (name="description")
	private String description;
	
	@Column(name="last_occured")
	private LocalDateTime lastOccured;
	
	@Column(name="streak")
	private long streak;
	
	public enum Occurances {
		DAILY, WEEKLY, MONTHLY, CUSTOM
	};
	
}
