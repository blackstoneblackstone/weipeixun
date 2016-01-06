package top.wexue.model;

/**
 * Created by lihb on 9/4/15.
 */
public class SessionInfo {
    private String ip;
    private String username;
    private String userId;
    private String id;
    private String qyname;
    private String qyheader;
    private String useravator;
    private String corpid;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUseravator() {
        return useravator;
    }

    public void setUseravator(String useravator) {
        this.useravator = useravator;
    }

    public String getQyheader() {
        return qyheader;
    }

    public void setQyheader(String qyheader) {
        this.qyheader = qyheader;
    }


    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getQyname() {
        return qyname;
    }

    public void setQyname(String qyname) {
        this.qyname = qyname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
