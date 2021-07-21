package cn.ag.channel.service.impl;

import cn.ag.channel.model.Book;
import cn.ag.channel.mapper.BookMapper;
import cn.ag.channel.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书本信息 服务实现类
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
