package us.zoom.async.controller;

import org.springframework.web.bind.annotation.*;
import us.zoom.async.entity.Result;
import us.zoom.async.entity.User;

/**
 * @Author Eden Ye
 * @Date 2019/3/26 12:15
 * @Description
 */
@RestController
public class RestUserController {

    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable(value = "id") String id){

        return null;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/user/login")
    public Result login(String username, String password){

        System.out.println("------------------");
        Result result = new Result();
        if ("admin".equals(username)&& "admin".equals(password)){
            result.setCode("200");
            result.setMsg("login success");
            result.setToken("token123456");
        }else {
            result.setCode("401");
            result.setMsg("login failed");
        }
        return result;
    }
}
