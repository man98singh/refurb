package com.houstondirectauto.refurb.exception;


import lombok.extern.slf4j.Slf4j;

/**
 * BadRequestException Class
 */
@Slf4j
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 3334261301168302623L;


	/**
	 * BadRequestException Method
	 *
	 * @param message String value
	 */
	public BadRequestException(String message) {

		super(message);

		log.debug(" BadRequestException{}", message);
	}
}
