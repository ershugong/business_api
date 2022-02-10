package cn.ag.channel.service.impl;

import cn.ag.channel.exception.MyException;
import cn.ag.channel.jsonResult.JsonResult;
import cn.ag.channel.mapper.AuthInfoMapper;
import cn.ag.channel.mapper.MenuInfoMapper;
import cn.ag.channel.mapper.RoleInfoMapper;
import cn.ag.channel.model.AuthInfo;
import cn.ag.channel.model.MenuInfo;
import cn.ag.channel.model.UserInfo;
import cn.ag.channel.mapper.UserInfoMapper;
import cn.ag.channel.service.IUserInfoService;
import cn.ag.channel.util.MD5Util;
import cn.ag.channel.util.RedisUtils;
import cn.ag.channel.vo.UserMenuInfoVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Autowired
    private AuthInfoMapper authInfoMapper;

    @Autowired
    private MenuInfoMapper menuInfoMapper;

    @Autowired
    private RedisUtils redisUtils;

    private final String USER_INFO_KEY = "userInfo";
    @Override
    public UserMenuInfoVO userLogin(UserInfo userInfo) {
        UserMenuInfoVO userMenuInfoVO = new UserMenuInfoVO();
        //将密码转换为加密数据
        String password = null;
        try {
            password = MD5Util.EncoderByMd5(userInfo.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //判断当前用户账号密码 是否正确
        UserInfo userInfoResult = userInfoMapper.selectOne(
                new LambdaQueryWrapper<UserInfo>()
                        .eq(UserInfo::getUserName,userInfo.getUserName())
                        .eq(UserInfo::getPassword,password));
        userInfoResult.setPassword("");
        if(userInfoResult == null){
            throw new MyException(481,"账号密码不正确!");
        }
        //获取当前用户可见的菜单
        List<MenuInfo> menuInfoList = userInfoMapper.getUserMenu(userInfoResult.getId());
        userMenuInfoVO.setMenuInfoList(menuInfoList);
        userMenuInfoVO.setUserInfo(userInfoResult);

        //把用户信息放置缓存
        redisUtils.set(USER_INFO_KEY + userInfoResult.getId().toString(),userMenuInfoVO);

        return userMenuInfoVO;
    }
}
