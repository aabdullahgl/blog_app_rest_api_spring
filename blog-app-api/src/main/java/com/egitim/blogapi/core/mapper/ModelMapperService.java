package com.egitim.blogapi.core.mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

	public ModelMapper forResponse();

	public ModelMapper forRequest();
}
