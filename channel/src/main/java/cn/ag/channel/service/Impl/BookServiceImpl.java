package cn.ag.channel.service.Impl;

import cn.ag.channel.model.Book;
import cn.ag.channel.mapper.BookMapper;
import cn.ag.channel.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cmx
 * @since 2021-07-20
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
    public Integer insertBatchSomeColumn(){
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        book.setId(1L);
        book.setPrice(BigDecimal.valueOf(1));
        book.setBookName("Java");
        Book book1 = new Book();
        book1.setId(2L);
        book1.setPrice(BigDecimal.valueOf(2));
        book1.setBookName("Java");
        Book book2 = new Book();
        book2.setId(3L);
        book2.setPrice(BigDecimal.valueOf(3));
        book2.setBookName("Java");
        bookList.add(book);
        bookList.add(book1);
        bookList.add(book2);
        return baseMapper.insertBatchSomeColumn(bookList);
    }

}
