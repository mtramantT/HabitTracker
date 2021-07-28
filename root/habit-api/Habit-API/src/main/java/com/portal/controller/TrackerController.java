package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.exception.TrackerNotFoundException;
import com.portal.model.Tracker;
import com.portal.repositorie.TrackerRepo;

@RestController
@RequestMapping("/tracker")
public class TrackerController {

	@Autowired
	private TrackerRepo repo;
	
	@GetMapping("/trackers")
	public List<Tracker> all() {
		return repo.findAll();
	}
	
	@PostMapping("/employees")
	public Tracker newTracker(@RequestBody Tracker tracker) {
		return repo.save(tracker);
	}
	
	@GetMapping("/trackers/{id}")
	public Tracker one(@PathVariable Long id) throws TrackerNotFoundException {
		return repo.findById(id)
				.orElseThrow(() -> new TrackerNotFoundException(id));
	}
	
	
}
