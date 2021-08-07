package com.portal.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
	public EntityModel<Tracker> newTracker(@RequestBody Tracker tracker) throws TrackerNotFoundException {
		Tracker newTracker = repo.save(tracker);
		return EntityModel.of(newTracker, //
				linkTo(methodOn(TrackerController.class).newTracker(tracker)).withSelfRel(),
				linkTo(methodOn(TrackerController.class).findOne(tracker.getId())).withRel("Tracker"));
	}
	
	@GetMapping("/trackers/{id}")
	public EntityModel<Tracker> findOne(@PathVariable Long id) throws TrackerNotFoundException {
		Tracker updated = repo.findById(id)
				.orElseThrow(() -> new TrackerNotFoundException(id));
		return EntityModel.of(updated, //
				linkTo(methodOn(TrackerController.class)).withSelfRel());
	}
	
	@GetMapping("/trackers/trackers")
	public CollectionModel<EntityModel<Tracker>> findAll() {
		List<Tracker> list = repo.findAll();
		List<EntityModel<Tracker>> entityCollection = 
				list.stream().map(model -> 
				EntityModel.of(model, linkTo(methodOn(TrackerController.class).findAll()).withSelfRel()))
					.collect(Collectors.toList()); 
		return CollectionModel.of(entityCollection);
	}
	
}
