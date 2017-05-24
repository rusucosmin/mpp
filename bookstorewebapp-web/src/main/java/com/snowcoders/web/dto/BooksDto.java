package com.snowcoders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 *  30/04/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private Set<BookDto> books;
}
