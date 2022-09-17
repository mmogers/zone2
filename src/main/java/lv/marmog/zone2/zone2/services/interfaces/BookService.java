package lv.marmog.zone2.zone2.services.interfaces;

import lv.marmog.zone2.zone2.DTO.BookDTO;

import java.util.List;

public interface BookService {

    public BookDTO addBook(BookDTO book);

    public List<BookDTO> getBooks();

    public BookDTO getBookById(Integer id);

    public BookDTO updateBook(BookDTO book, Integer id);

    public void deleteBook(Integer id);

}
