package cn.ag.channel.controller;

import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.jsonResult.PageList;
import cn.ag.channel.model.Book;
import cn.ag.channel.query.BookQuery;
import cn.ag.channel.service.ITestService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "sayHello";
    }

    /**
     * 查看所有信息
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult list(){
        return JsonResult.success(testService.list());
    }

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult pageList(@RequestBody BookQuery query) {
        Page<Book> page = new Page<Book>(query.getPageIndex(),query.getPageSize());
        IPage pageResult = testService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }

    @RequestMapping("/redisTest")
    public JsonResult redisTest(){
        Book book = new Book();
        book.setBookName("西游记");
        book.setPrice(BigDecimal.valueOf(12.05));
        redisTemplate.opsForValue().set("book",book);
        return JsonResult.success(redisTemplate.opsForValue().get("book"));
    }
}
