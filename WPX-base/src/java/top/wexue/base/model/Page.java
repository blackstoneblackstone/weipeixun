package top.wexue.base.model;

/**
 * Created by lihb on 12/16/15.
 */
public class Page {
    private  int startPage=1;
    private  int pageSize=5;

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart(){
        return (startPage-1)*pageSize;
    }
}
