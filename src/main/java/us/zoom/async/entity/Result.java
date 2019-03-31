package us.zoom.async.entity;

import lombok.Data;

/**
 * Created by lanbo on 2019/3/27.
 */
@Data
public class Result {
    private String code;
    private String msg;
    private String token;

    public Result() {
    }

    public Result(String code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = token;
    }
}
