package com.apps.wave.news.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class Util {
	public static <S, D> List<D> convertToDtoList(List<S> sourceList, Class<D> destinationType) {

		ModelMapper mapper = new ModelMapper();
		return sourceList.stream().map(item -> {

			return mapper.map(item, destinationType);
		}).collect(Collectors.toList());

	}

	public static <T> T convertToDto(Object entity, Class<T> destinationType) {
		ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(entity, destinationType);

	}
}
