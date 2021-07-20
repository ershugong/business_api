package cn.ag.channel.jsonResult;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageList<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     */
    public static <T> PageList<T> restPage(IPage<T> pageResult) {
        PageList<T> result = new PageList<>();
        result.setPageNum(new Long(pageResult.getCurrent()).intValue());
        result.setPageSize(new Long(pageResult.getSize()).intValue());
        result.setTotal(pageResult.getTotal());
        result.setTotalPage(new Long(pageResult.getTotal()/pageResult.getSize()+1).intValue());
        result.setList(pageResult.getRecords());
        return result;
    }

    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     */
    public static <T> PageList<T> restPage(Page<T> pageResult) {
        PageList<T> result = new PageList<>();
        result.setPageNum(new Long(pageResult.getCurrent()).intValue());
        result.setPageSize(new Long(pageResult.getSize()).intValue());
        result.setTotal(pageResult.getTotal());
        result.setTotalPage(new Long(pageResult.getTotal()/pageResult.getSize()+1).intValue());
        result.setList(pageResult.getRecords());
        return result;
    }
}
