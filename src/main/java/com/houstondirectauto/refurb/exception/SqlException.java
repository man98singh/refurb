package com.houstondirectauto.refurb.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * EntityNotFoundException Class
 */
@Slf4j
public class SqlException extends Exception {

	private static final long serialVersionUID = -7374235275003296287L;


	/**
	 * EntityNotFoundException Method
	 *
	 * @param message String value
	 */
	public SqlException(String message) {
		super(message);

		log.debug(" EntityNotFoundException{}", message);
	}
}