package cn.ag.channel.mapper;

import cn.ag.channel.model.MenuInfo;
import cn.ag.channel.model.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author cmx
 * @since 2021-07-21
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select mi.* from tb_user_info ui,tb_user_role_relation ur,tb_role_auth_relation ra,tb_auth_menu_relation am,tb_menu_info mi where " +
            " ui.id = ur.user_id and ur.role_id = ra.role_id and ra.auth_id = am.auth_id and am.menu_id = mi.id" +
            " and ui.id = #{userId} group by mi.id")
    List<MenuInfo> getUserMenu(@Param("userId") long userId);
}
