package com.snowcoders.web.converter;

import com.snowcoders.core.model.BaseEntity;
import com.snowcoders.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

