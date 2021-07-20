package cn.ag.channel.jsonResult;

public class BaseQuery {
    //有公共属性-分页
    private Integer pageIndex = 1; //当前页
    private Integer pageSize = 10; //每页显示多少条

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
