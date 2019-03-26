package us.zoom.async.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
