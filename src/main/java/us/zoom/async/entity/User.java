package us.zoom.async.entity;

import lombok.Data;

/**
 * @Author Eden Ye
 * @Date 2019/3/26 12:12
 * @Description
 */
@Data
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private int role;
}
