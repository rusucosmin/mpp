package com.snowcoders.web.converter;

import com.snowcoders.core.model.BaseEntity;
import com.snowcoders.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends ConverterGeneric<Model, Dto> {
}