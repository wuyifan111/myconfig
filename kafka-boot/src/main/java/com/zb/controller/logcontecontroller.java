package com.zb.controller;


import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "这是一个测试日志类",description = "完成用户增删改查")
public class logcontecontroller {
    @Autowired
    private KafkaTemplate KafkaTemplate;


    private static List<user> list=new ArrayList<>();

    static {
        list.add(new user(1,"张三"));
        list.add(new user(2,"李四"));
        list.add(new user(3,"王五"));
        list.add(new user(4,"赵六"));
    }

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ApiOperation(value = "用户查询方法",notes = "查询用户列表")
    public Dto nn(){
        return DtoUtil.returnSuccess("ok",list);
    }
    @GetMapping("/add")
    public String aa(){
        for (int i=0;i<10;i++){

                    KafkaTemplate.send("deservice","dm","发送的数据-----"+i);
        }
        return "ok";
    }



    @ApiOperation(value = "用户删除方法",notes = "删除用户列表")
    @ApiImplicitParam(value = "用户编号",required = true,name = "id",dataType = "int",paramType = "path")
    @RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
    public Dto remove(@PathVariable("id") int id){
        for (user u :list){
            if(u.getId()==id){
                list.remove(u);
                return DtoUtil.returnSuccess("ok");
            }
        }
        return DtoUtil.returnSuccess("input");
    }

    @ApiOperation(value = "根据编号查询用户方法",notes = "根据编号查询用户列表")
    @ApiImplicitParam(value = "用户编号",required = true,name = "id",dataType = "int",paramType = "path")
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Dto get(@PathVariable("id") int id){
        for (user u :list){
            if(u.getId()==id){

                return DtoUtil.returnSuccess("ok",u);
            }
        }
        return DtoUtil.returnSuccess("input");
    }
    @ApiOperation(value = "修改用户方法",notes = "修改用户用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户编号",required = true,name = "id",dataType = "int",paramType = "path"),
            @ApiImplicitParam(value = "用户对象",required = true,name = "u",dataType = "user")
    })
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Dto update(@PathVariable("id") Integer id,@RequestBody user u){
        for (int i=0;i<list.size();i++){
            user u1=list.get(i);
            if(u1.getId()==id){
                    list.set(i,u);
                return DtoUtil.returnSuccess("ok",u);
            }
        }
        return DtoUtil.returnSuccess("input");

    }

    @ApiOperation(value = "添加用户方法",notes = "添加用户用户列表")
    @ApiImplicitParam(value = "用户编号",required = true,name = "u",dataType = "user")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Dto insert(@RequestBody user u){
        list.add(u);
        return DtoUtil.returnSuccess("ok");
    }
}





