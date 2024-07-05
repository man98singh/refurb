package com.houstondirectauto.refurb.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * ServiceUnavailableException Class
 */
@Slf4j
public class ServiceUnavailableException extends Exception {

	private static final long serialVersionUID = 3334261301168302623L;


	/**
	 * ServiceUnavailableException Method
	 *
	 * @param message String value
	 */
	public ServiceUnavailableException(String message) {

		super(message);

		log.debug(" ServiceUnavailableException{}", message);
	}
}

