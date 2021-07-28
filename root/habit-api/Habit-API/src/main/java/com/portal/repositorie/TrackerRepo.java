package com.portal.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.model.Tracker;

public interface TrackerRepo extends JpaRepository<Tracker, Long> {

}
