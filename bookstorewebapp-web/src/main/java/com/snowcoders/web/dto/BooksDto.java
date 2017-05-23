package com.snowcoders.web.dto;

import com.snowcoders.core.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *  30/04/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private List<Book> books;
}
