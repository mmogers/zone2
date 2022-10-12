package lv.marmog.zone2.zone2.mappers.mapperInterface;


import lv.marmog.zone2.zone2.DTO.BookDTO;
import lv.marmog.zone2.zone2.models.Book;

public interface BookMapperInterface {

     BookDTO bookToDTO(Book book);

     Book DTOToBook(BookDTO bookDTO);


}
