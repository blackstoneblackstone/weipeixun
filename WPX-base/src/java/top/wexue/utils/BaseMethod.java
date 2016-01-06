package top.wexue.utils;

import top.wexue.model.SessionInfo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lihb on 10/4/15.
 */
public class BaseMethod {

    public static String createUUID(String type){
        UUID uuid = UUID.randomUUID();
        return type+uuid.toString();
    }
    public static long getCurrentTime(){
        return new Date().getTime();
    }


}
