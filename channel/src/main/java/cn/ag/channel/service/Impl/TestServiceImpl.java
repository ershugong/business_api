package cn.ag.channel.service.Impl;

import cn.ag.channel.mapper.TestMapper;
import cn.ag.channel.model.Book;
import cn.ag.channel.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl extends ServiceImpl<TestMapper, Book> implements ITestService {

}
