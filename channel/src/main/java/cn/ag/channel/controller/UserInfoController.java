package cn.ag.channel.controller;

import cn.ag.channel.service.IUserInfoService;
import cn.ag.channel.model.UserInfo;
import cn.ag.channel.query.UserInfoQuery;
import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.jsonResult.PageList;
import cn.ag.channel.util.MD5Util;
import cn.ag.channel.vo.UserMenuInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@RestController
@Api(tags = "用户信息")
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    public IUserInfoService userInfoService;

    /**
     * 保存和修改公用的
     * @param userInfo  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody UserInfo userInfo){
        try {
            userInfo.setPassword(MD5Util.EncoderByMd5(userInfo.getPassword()));
            if(userInfo.getId()!=null){
                    userInfoService.updateById(userInfo);
            }else{
                    userInfoService.save(userInfo);
            }
            return JsonResult.success(userInfo);
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
                userInfoService.removeById(id);
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
    public JsonResult<UserInfo> get(@PathVariable("id")Long id) {
        return JsonResult.success(userInfoService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<UserInfo>> list(){
        return JsonResult.success(userInfoService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<UserInfo>> pageList(@RequestBody UserInfoQuery query) {
        Page<UserInfo> page = new Page<UserInfo>(query.getPageIndex(),query.getPageSize());
        IPage<UserInfo> pageResult = userInfoService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }

    @ApiOperation("用户登录")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public JsonResult<UserMenuInfoVO> userLogin(@RequestBody UserInfo userInfo){
        return JsonResult.success(userInfoService.userLogin(userInfo));
    }
}