package cn.ag.channel.controller;

import cn.ag.channel.service.IRoleAuthRelationService;
import cn.ag.channel.model.RoleAuthRelation;
import cn.ag.channel.query.RoleAuthRelationQuery;
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
@Api(tags = "角色权限关系信息")
@RequestMapping("/roleAuthRelation")
public class RoleAuthRelationController {
    @Autowired
    public IRoleAuthRelationService roleAuthRelationService;

    /**
     * 保存和修改公用的
     * @param roleAuthRelation  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody RoleAuthRelation roleAuthRelation){
        try {
            if(roleAuthRelation.getId()!=null){
                    roleAuthRelationService.updateById(roleAuthRelation);
            }else{
                    roleAuthRelationService.save(roleAuthRelation);
            }
            return JsonResult.success(roleAuthRelation);
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
                roleAuthRelationService.removeById(id);
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
    public JsonResult<RoleAuthRelation> get(@PathVariable("id")Long id) {
        return JsonResult.success(roleAuthRelationService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<RoleAuthRelation>> list(){
        return JsonResult.success(roleAuthRelationService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<RoleAuthRelation>> pageList(@RequestBody RoleAuthRelationQuery query) {
        Page<RoleAuthRelation> page = new Page<RoleAuthRelation>(query.getPageIndex(),query.getPageSize());
        IPage<RoleAuthRelation> pageResult = roleAuthRelationService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}