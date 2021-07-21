package cn.ag.channel.controller;

import cn.ag.channel.service.IAuthMenuRelationService;
import cn.ag.channel.model.AuthMenuRelation;
import cn.ag.channel.query.AuthMenuRelationQuery;
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
@Api(tags = "权限菜单关系信息")
@RequestMapping("/authMenuRelation")
public class AuthMenuRelationController {
    @Autowired
    public IAuthMenuRelationService authMenuRelationService;

    /**
     * 保存和修改公用的
     * @param authMenuRelation  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody AuthMenuRelation authMenuRelation){
        try {
            if(authMenuRelation.getId()!=null){
                    authMenuRelationService.updateById(authMenuRelation);
            }else{
                    authMenuRelationService.save(authMenuRelation);
            }
            return JsonResult.success(authMenuRelation);
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
                authMenuRelationService.removeById(id);
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
    public JsonResult<AuthMenuRelation> get(@PathVariable("id")Long id) {
        return JsonResult.success(authMenuRelationService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<AuthMenuRelation>> list(){
        return JsonResult.success(authMenuRelationService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<AuthMenuRelation>> pageList(@RequestBody AuthMenuRelationQuery query) {
        Page<AuthMenuRelation> page = new Page<AuthMenuRelation>(query.getPageIndex(),query.getPageSize());
        IPage<AuthMenuRelation> pageResult = authMenuRelationService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}