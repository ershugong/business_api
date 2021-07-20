package cn.ag.channel.controller;

import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.jsonResult.PageList;
import cn.ag.channel.model.Book;
import cn.ag.channel.model.User;
import cn.ag.channel.query.BookQuery;
import cn.ag.channel.service.ITestService;
import cn.ag.channel.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        book.setId((long) 12);
        redisTemplate.opsForValue().set("book",book);
        return JsonResult.success(redisTemplate.opsForValue().get("book"));
    }

    @RequestMapping("/login")
    public JsonResult login(){
        User user = new User();
        user.setId("1234567");
        user.setUserName("孙悟空");
        user.setPassword("123");
        redisTemplate.opsForValue().set(user.getId(),user,30 * 60, TimeUnit.SECONDS);
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        String token = JwtUtils.createJwtToken(map);
        return JsonResult.success("Bearer " + token);
    }
}
