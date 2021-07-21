package cn.ag.channel.controller;

import cn.ag.channel.service.IUserService;
import cn.ag.channel.model.User;
import cn.ag.channel.query.UserQuery;
import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.jsonResult.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户信息接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    public IUserService userService;

    /**
     * 保存和修改公用的
     * @param user  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("添加用户")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody User user){
        try {
            if(user.getId()!=null){
                    userService.updateById(user);
            }else{
                    userService.save(user);
            }
            return JsonResult.success(user);
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
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Long id){
        try {
                userService.removeById(id);
            return JsonResult.success(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
    }

    //获取通过id
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JsonResult get(@PathVariable("id")Long id) {
        return JsonResult.success(userService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult list(){
        return JsonResult.success(userService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult pageList(@RequestBody UserQuery query) {
        Page<User> page = new Page<User>(query.getPageIndex(),query.getPageSize());
        IPage pageResult = userService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}