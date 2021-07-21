package cn.ag.channel.controller;

import cn.ag.channel.service.IRoleInfoService;
import cn.ag.channel.model.RoleInfo;
import cn.ag.channel.query.RoleInfoQuery;
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
@Api(tags = "角色信息")
@RequestMapping("/roleInfo")
public class RoleInfoController {
    @Autowired
    public IRoleInfoService roleInfoService;

    /**
     * 保存和修改公用的
     * @param roleInfo  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody RoleInfo roleInfo){
        try {
            if(roleInfo.getId()!=null){
                    roleInfoService.updateById(roleInfo);
            }else{
                    roleInfoService.save(roleInfo);
            }
            return JsonResult.success(roleInfo);
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
                roleInfoService.removeById(id);
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
    public JsonResult<RoleInfo> get(@PathVariable("id")Long id) {
        return JsonResult.success(roleInfoService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<RoleInfo>> list(){
        return JsonResult.success(roleInfoService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<RoleInfo>> pageList(@RequestBody RoleInfoQuery query) {
        Page<RoleInfo> page = new Page<RoleInfo>(query.getPageIndex(),query.getPageSize());
        IPage<RoleInfo> pageResult = roleInfoService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}