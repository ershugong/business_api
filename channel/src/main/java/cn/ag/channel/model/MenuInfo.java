package cn.ag.channel.model;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 菜单信息
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
@TableName("tb_menu_info")
@ApiModel(value="MenuInfo对象", description="菜单信息")
public class MenuInfo extends Model<MenuInfo> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "菜单名")
    private String menuName;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "上级菜单id")
    private Long parentId;

    @ApiModelProperty(value = "是否删除 0否  1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "菜单链接")
    private String menuUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
        "id=" + id +
        ", menuName=" + menuName +
        ", menuCode=" + menuCode +
        ", parentId=" + parentId +
        ", deleted=" + deleted +
        ", menuUrl=" + menuUrl +
        "}";
    }
}
