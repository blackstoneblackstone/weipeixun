package top.wexue.base.model;

/**
 * Created by lihb on 11/6/15.
 */
public class Course {
    private String id;
    private String type;//1:公开课2：必修课
    private String name;
    private String icon;
    private String desc;
    private String expectperson;
    private String starttime;
    private String endtime;
    private String projectid;
    private String place;
    private String[] fuzeren;
    private String[] zhujiangren;
    private String[] canyuren;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String[] getFuzeren() {
        return fuzeren;
    }

    public void setFuzeren(String[] fuzeren) {
        this.fuzeren = fuzeren;
    }

    public String[] getZhujiangren() {
        return zhujiangren;
    }

    public void setZhujiangren(String[] zhujiangren) {
        this.zhujiangren = zhujiangren;
    }

    public String[] getCanyuren() {
        return canyuren;
    }

    public void setCanyuren(String[] canyuren) {
        this.canyuren = canyuren;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExpectperson() {
        return expectperson;
    }

    public void setExpectperson(String expectperson) {
        this.expectperson = expectperson;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
