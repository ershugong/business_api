package cn.ag.channel.service;

import cn.ag.channel.model.UserInfo;
import cn.ag.channel.vo.UserMenuInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
public interface IUserInfoService extends IService<UserInfo> {
    UserMenuInfoVO userLogin(UserInfo userInfo);
}
