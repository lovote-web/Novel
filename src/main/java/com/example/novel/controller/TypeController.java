package com.example.novel.controller;

import com.example.novel.po.Type;
import com.example.novel.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//获取分类名
@RestController
public class TypeController {

    @Autowired
    TypeService typeService;

    /**
     * 返回所有的分类信息
     * @return
     */
    @RequestMapping(value = "/getAllType",method = RequestMethod.GET)
    public List<Type> getAllType(){
        return typeService.findAll();
    }

    @RequestMapping(value = "/type/{id}",method = RequestMethod.GET)
    public Type getType(@PathVariable Long id){
        return typeService.getOneType(id);
    }
}
