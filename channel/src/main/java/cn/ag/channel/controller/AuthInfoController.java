package cn.ag.channel.controller;

import cn.ag.channel.service.IAuthInfoService;
import cn.ag.channel.model.AuthInfo;
import cn.ag.channel.query.AuthInfoQuery;
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
@Api(tags = "权限信息")
@RequestMapping("/authInfo")
public class AuthInfoController {
    @Autowired
    public IAuthInfoService authInfoService;

    /**
     * 保存和修改公用的
     * @param authInfo  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody AuthInfo authInfo){
        try {
            if(authInfo.getId()!=null){
                    authInfoService.updateById(authInfo);
            }else{
                    authInfoService.save(authInfo);
            }
            return JsonResult.success(authInfo);
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
                authInfoService.removeById(id);
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
    public JsonResult<AuthInfo> get(@PathVariable("id")Long id) {
        return JsonResult.success(authInfoService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<AuthInfo>> list(){
        return JsonResult.success(authInfoService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<AuthInfo>> pageList(@RequestBody AuthInfoQuery query) {
        Page<AuthInfo> page = new Page<AuthInfo>(query.getPageIndex(),query.getPageSize());
        IPage<AuthInfo> pageResult = authInfoService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}