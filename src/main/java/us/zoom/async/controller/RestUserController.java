package us.zoom.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import us.zoom.async.entity.Result;
import us.zoom.async.entity.User;

/**
 * @Author Eden Ye
 * @Date 2019/3/26 12:15
 * @Description
 */
@Controller
public class RestUserController {

    @ResponseBody
    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }
    @ResponseBody
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable(value = "id") String id){

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){

        System.out.println("------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>------------");
        Result result = new Result();
        if ("admin".equals(user.getUsername())&& "admin".equals(user.getPassword())){
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
