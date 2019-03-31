package us.zoom.async.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import us.zoom.async.entity.Result;
import us.zoom.async.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Eden Ye
 * @Date 2019/3/26 12:15
 * @Description
 */
@Controller
public class RestUserController {

    private Logger logger = LoggerFactory.getLogger(RestUserController.class);
    @ResponseBody
    @GetMapping(value = "/test")
    public Result test(){
        logger.info("---- test hit -----");
        Result result = new Result("200","test","");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Result login(HttpServletRequest request, HttpServletResponse response,@RequestBody User user){

        logger.info("------user login hit -----------");
        logger.info("username --:"+request.getParameter("username"));
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

    @ResponseBody
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public Result test1(){
        logger.info("------/test1 hit -----------");
        return new Result("200","test1 success","");
    }

    @ResponseBody
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public Result test2(){
        logger.info("------/test2 hit -----------");
        return new Result("200","test2 success","");
    }


}
