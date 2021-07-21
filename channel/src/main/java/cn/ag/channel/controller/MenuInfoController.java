package cn.ag.channel.controller;

import cn.ag.channel.service.IMenuInfoService;
import cn.ag.channel.model.MenuInfo;
import cn.ag.channel.query.MenuInfoQuery;
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
@Api(tags = "菜单信息")
@RequestMapping("/menuInfo")
public class MenuInfoController {
    @Autowired
    public IMenuInfoService menuInfoService;

    /**
     * 保存和修改公用的
     * @param menuInfo  传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation("保存和修改公用的")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JsonResult save(@RequestBody MenuInfo menuInfo){
        try {
            if(menuInfo.getId()!=null){
                    menuInfoService.updateById(menuInfo);
            }else{
                    menuInfoService.save(menuInfo);
            }
            return JsonResult.success(menuInfo);
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
                menuInfoService.removeById(id);
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
    public JsonResult<MenuInfo> get(@PathVariable("id")Long id) {
        return JsonResult.success(menuInfoService.getById(id));
    }


    /**
    * 查看所有信息
    * @return
    */
    @ApiOperation("查看所有信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<MenuInfo>> list(){
        return JsonResult.success(menuInfoService.list());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation("分页查询数据")
    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    public JsonResult<PageList<MenuInfo>> pageList(@RequestBody MenuInfoQuery query) {
        Page<MenuInfo> page = new Page<MenuInfo>(query.getPageIndex(),query.getPageSize());
        IPage<MenuInfo> pageResult = menuInfoService.page(page);
        return JsonResult.success(PageList.restPage(pageResult));
    }
}