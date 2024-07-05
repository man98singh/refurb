package com.houstondirectauto.refurb.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * UnauthorizedException Class
 */
@Slf4j
public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = 6353900531222752213L;


	/**
	 * UnauthorizedException Method
	 *
	 * @param message String value
	 */
	public UnauthorizedException(String message) {
		super(message);

		log.debug(" UnauthorizedException{}", message);
	}

    
}
