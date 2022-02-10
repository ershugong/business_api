package cn.ag.channel.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface SelfMapper<T> extends BaseMapper<T> {
    int insertBatchSomeColumn(Collection<T> entityList);
}
