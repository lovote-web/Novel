package com.example.novel.controller;

import com.example.novel.po.Billboard;
import com.example.novel.service.BillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BillboardController {

    @Autowired
    BillboardService billboardService;

    @RequestMapping(value = "/billboards",method = RequestMethod.GET)
    public Map<String,Object> getAllBillboard(){
        List<Billboard> list = billboardService.getAllBillboard();
        Map<String,Object> map=new HashMap<>();
        if(list!=null){
            map.put("code","200");
            map.put("message","获取成功");
            map.put("billboards",list);
        }else{
            map.put("code","404");
            map.put("message","获取失败");
        }
        return map;
    }

    @RequestMapping(value = "/billboard/{id}",method = RequestMethod.GET)
    public Map<String,Object> getOneBillboard(@PathVariable Long id){
        Billboard billboard=billboardService.BillboardAll(id);
        Map<String,Object> map=new HashMap<>();
        if(billboard != null){
            map.put("code","200");
            map.put("message","获取成功");
            map.put("billboard",billboard);
        }else{
            map.put("code","404");
            map.put("message","获取失败");
        }
        return map;
    }
}
