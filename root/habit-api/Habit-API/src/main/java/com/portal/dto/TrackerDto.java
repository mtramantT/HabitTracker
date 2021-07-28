package com.portal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackerDto {

	private Long id;
	private String habit;
	private String description;
	private LocalDateTime lastOccured;	
	private long streak;
	
}
