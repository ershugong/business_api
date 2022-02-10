package cn.ag.channel.controller;

import cn.ag.channel.service.IBookService;
import cn.ag.channel.model.Book;
import cn.ag.channel.query.BookQuery;
import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.jsonResult.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@RestController
@Api(tags = "书本信息")
@RequestMapping("/book")
public class BookController {
    @Autowired
    public IBookService bookService;

    /**
     * 保存和修改公用的
     * @param book  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody Book book){
        try {
            if(book.getId()!=null){
                    bookService.updateById(book);
            }else{
                    bookService.save(book);
            }
            return JsonResult.success(book);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("保存失败");
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation("删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Long id){
        try {
                bookService.removeById(id);
            return JsonResult.success(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
    }

    /**
    * 通过id获取对象信息
    * @param id
    * @return
    */
    @ApiOperation("通过id获取对象信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult<Book> get(@PathVariable("id")Long id) {
        return JsonResult.success(bookService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<Book>> list(){
        return JsonResult.success(bookService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<Book>> pageList(@RequestBody BookQuery query) {
        Page<Book> page = new Page<Book>(query.getPageIndex(),query.getPageSize());
        IPage<Book> pageResult = bookService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}