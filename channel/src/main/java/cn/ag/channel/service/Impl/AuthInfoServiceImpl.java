package cn.ag.channel.service.impl;

import cn.ag.channel.model.AuthInfo;
import cn.ag.channel.mapper.AuthInfoMapper;
import cn.ag.channel.service.IAuthInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限信息 服务实现类
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
@Service
public class AuthInfoServiceImpl extends ServiceImpl<AuthInfoMapper, AuthInfo> implements IAuthInfoService {

}
