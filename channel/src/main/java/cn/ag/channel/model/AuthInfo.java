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
 * 权限信息
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
@TableName("tb_auth_info")
@ApiModel(value="AuthInfo对象", description="权限信息")
public class AuthInfo extends Model<AuthInfo> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private String authName;

    @ApiModelProperty(value = "是否删除  0否  1是")
    private Integer deleted;

    @ApiModelProperty(value = "权限描述")
    private String decription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AuthInfo{" +
        "id=" + id +
        ", authName=" + authName +
        ", deleted=" + deleted +
        ", decription=" + decription +
        "}";
    }
}
