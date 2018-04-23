package entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {
    private long total;//总记录数
    private List rows;//分页的数据列表集合

    public PageResult(long total,List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }


    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
