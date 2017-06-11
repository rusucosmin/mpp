package com.snowcoders.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowcoders.core.model.Book;
import com.snowcoders.core.service.BookService;
import com.snowcoders.web.converter.BookConverter;
import com.snowcoders.web.dto.BookDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by cosmin on 04/06/2017.
 */
public class BookControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookConverter bookConverter;

    private Book book1, book2;
    private BookDto bookDto1, bookDto2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .build();
        initData();
    }

    @Test
    public void getBooks() throws Exception {
        List<Book> bookList = Arrays.asList(book1, book2);
        Set<BookDto> bookDtoSet = new HashSet<>(Arrays.asList(bookDto1, bookDto2));
        when(bookService.findAll()).thenReturn(bookList);
        when(bookConverter.convertModelsToDtos(bookList)).thenReturn(bookDtoSet);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.books", hasSize(2)))
                .andExpect(jsonPath("$.books[0].title", anyOf(is("Models"), is("The Game"))))
                .andExpect(jsonPath("$.books[1].title", anyOf(is("Models"), is("The Game"))))
                .andExpect(jsonPath("$.books[0].author", anyOf(is("Neil Strauss"), is("Mark Manson"))))
                .andExpect(jsonPath("$.books[1].author", anyOf(is("Neil Strauss"), is("Mark Manson"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("Result: " + result);

        verify(bookService, times(1)).findAll();
        verify(bookConverter, times(1)).convertModelsToDtos(bookList);
        verifyNoMoreInteractions(bookService, bookConverter);
    }

    @Test
    public void updateBook() throws Exception {
        when(bookService.updateBook(book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getPrice()))
                .thenReturn(book1);
        when(bookConverter.convertModelToDto(book1))
                .thenReturn(bookDto1);

        Map<String, BookDto> bookDtoMap = new HashMap<>();
        bookDtoMap.put("book", bookDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/books/{bookId}", book1.getId(), bookDto1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(bookDtoMap)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.book.title", is("Models")))
                .andExpect(jsonPath("$.book.author", is("Mark Manson")))
                .andExpect(jsonPath("$.book.price", is(1.0)));
        verify(bookService, times(1)).updateBook(book1.getId(),
                book1.getTitle(), book1.getAuthor(), book1.getPrice());
        verify(bookConverter, times(1)).convertModelToDto(book1);
        verifyNoMoreInteractions(bookService, bookConverter);
    }

    @Test
    public void createBook() throws Exception {
        when(bookService.createBook(book1.getTitle(),
                book1.getAuthor(), book1.getPrice()))
                .thenReturn(book1);
        when(bookConverter.convertModelToDto(book1))
                .thenReturn(bookDto1);

        Map<String, BookDto> bookDtoMap= new HashMap<>();
        bookDtoMap.put("book", bookDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/books")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(bookDtoMap)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.book.title", is("Models")))
                .andExpect(jsonPath("$.book.author", is("Mark Manson")))
                .andExpect(jsonPath("$.book.price", is(1.0)))
                .andExpect(jsonPath("$.book.id", is(1)));

        verify(bookService, times(1)).createBook(book1.getTitle(), book1.getAuthor(), book1.getPrice());
        verify(bookConverter, times(1)).convertModelToDto(book1);
        verifyNoMoreInteractions(bookService, bookConverter);
    }

    @Test
    public void deleteBook() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/books/{bookId}", book1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("Results: " + result);

        verify(bookService, times(1)).deleteBook(book1.getId());
        verifyNoMoreInteractions(bookService, bookConverter);
    }

    private String toJsonString(Map<String, BookDto> studentDtoMap) {
        try {
            return new ObjectMapper().writeValueAsString(studentDtoMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void initData() {
        book1 = Book.builder()
                .title("Models")
                .author("Mark Manson")
                .price(1.0)
                .build();
        book1.setId(1l);
        book2 = Book.builder()
                .title("The Game")
                .author("Neil Strauss")
                .price(2.0)
                .build();
        book2.setId(2l);
        bookDto1 = createBookDto(book1);
        bookDto2 = createBookDto(book2);
    }

    private BookDto createBookDto(Book book) {
        BookDto bookDto = BookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
        bookDto.setId(book.getId());
        return bookDto;
    }
}