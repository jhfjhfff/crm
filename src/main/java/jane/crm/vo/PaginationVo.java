package jane.crm.vo;

import java.util.List;

/**
 * @author zy
 */
/*
    通用vo
 */
public class PaginationVo<T> {

    //总的记录条数
    private int total;

    //返回给前端的list
    private List<T> dataList;

    @Override
    public String toString() {
        return "PaginationVo{" +
                "total=" + total +
                ", dataList=" + dataList +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public PaginationVo() {
    }
}
