package com.houstondirectauto.refurb.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperUtil {

	private MapperUtil() {
		throw new IllegalStateException("Constants.UTILITY_CLASS");
	}

	/**
	 * Convert Model To Entity
	 * 
	 * @param <T>
	 * @param source
	 * @param destinationType
	 * @return
	 */
	public static <T> T convertModelToEntity(Object source, Class<T> destinationType) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, destinationType);
	}

	/**
	 * Convert Entity To Model
	 * 
	 * @param <T>
	 * @param source
	 * @param destinationType
	 * @return
	 */
	public static <T> T convertEntityToModel(Object source, Class<T> destinationType) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(source, destinationType);
	}

	/**
	 * Convert Stringfy Json To Object
	 * 
	 * @param <T>
	 * @param object
	 * @param classType
	 * @return
	 */
	public static <T> T convertStringfyJsonToObject(String object, Class<T> classType) {

		try {
			return new ObjectMapper().readValue(object, classType);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return null;

	}

}

