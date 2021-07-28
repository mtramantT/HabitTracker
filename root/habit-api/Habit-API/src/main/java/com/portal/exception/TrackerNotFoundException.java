package com.portal.exception;

public class TrackerNotFoundException extends Exception {

	private static final long serialVersionUID = 6411851971749986007L;
	
	public TrackerNotFoundException(Long id) {
		super("Could not find tracker: " + id);
	}

}
