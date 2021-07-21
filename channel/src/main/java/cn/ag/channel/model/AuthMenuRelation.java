package cn.ag.channel.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 权限菜单关系信息
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
@TableName("tb_auth_menu_relation")
@ApiModel(value="AuthMenuRelation对象", description="权限菜单关系信息")
public class AuthMenuRelation extends Model<AuthMenuRelation> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "菜单id")
    private Long menuId;

    @ApiModelProperty(value = "权限id")
    private Long authId;

    @ApiModelProperty(value = "是否删除  0否 1是")
    private Integer deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AuthMenuRelation{" +
        "id=" + id +
        ", menuId=" + menuId +
        ", authId=" + authId +
        ", deleted=" + deleted +
        "}";
    }
}
