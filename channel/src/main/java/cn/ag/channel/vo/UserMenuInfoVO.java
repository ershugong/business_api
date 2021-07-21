package cn.ag.channel.vo;

import cn.ag.channel.model.MenuInfo;
import cn.ag.channel.model.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户菜单信息")
public class UserMenuInfoVO {
    @ApiModelProperty("用户信息")
    private UserInfo userInfo;
    @ApiModelProperty("菜单信息")
    private List<MenuInfo> menuInfoList;
}
