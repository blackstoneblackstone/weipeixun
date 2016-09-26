package top.wexue.wpx.model;


import lombok.Data;
import top.wexue.wpx.api.WpxAPI;

/**
 * Created by lihb on 9/4/15.
 */
@Data
public class SessionInfo {
    private String ip;
    private String username;
    private String userId;
    private String id;
    private String qyname;
    private String qyheader;
    private String useravator;
    private String corpid;
}
