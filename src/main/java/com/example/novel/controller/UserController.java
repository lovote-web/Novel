package com.example.novel.controller;

import com.example.novel.po.Book;
import com.example.novel.service.BookService;
import com.example.novel.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    BookService bookService;

    /**
     * 添加到书架
     * @param id
     * @param nId
     * @return
     */
    @RequestMapping(value = "/addBook/{id}/{nId}",method = RequestMethod.GET)
    public Map<String,Object> bookshelf(@PathVariable Long id,@PathVariable("nId") Long nId){
        Map<String,Object> map=new HashMap<>();
        int a=bookService.joinBookshelf(id, nId);
        if (a == 1){
            map.put("code","200");
            map.put("message","添加成功");
        }else if (a == 2){
            map.put("code","404");
            map.put("message","用户名不存在");
        }else if(a == 3) {
            map.put("code","404");
            map.put("message","小说不存在");
        }else{
            map.put("code","400");
            map.put("message","添加失败");
        }
        return map;
    }

    /**
     * 个人书架
     * @param id
     * @return
     */
    @RequestMapping(value = "/Bookshelf/{id}",method = RequestMethod.GET)
    public List<Book> books(@PathVariable Long id){
        return bookService.getBookshelf(id);
    }

    /**
     * 判断小说是否已加入书架
     * @param id
     * @param nId
     * @return
     */
    @RequestMapping(value = "/InBookShelf/{id}/{nId}",method = RequestMethod.GET)
    public Map<String,Object> IsExist(@PathVariable Long id,@PathVariable Long nId){
        Map<String,Object> map=new HashMap<>();
        if(bookService.isExist(id, nId)){
            map.put("code",1);
            map.put("message","存在");
        }else {
            map.put("code",0);
            map.put("message","不存在");
        }
        return map;
    }

    /**
     * 移除小说
     * @param id
     * @param nId
     * @return
     */
    @RequestMapping(value = "/deleteBook/{id}/{nId}", method = RequestMethod.GET)
    public Map<String,Object> deleteBook(@PathVariable Long id,@PathVariable Long nId){
        Map<String,Object> map=new HashMap<>();
        if(bookService.deleteBook(id, nId)>0){
            map.put("code","200");
            map.put("message","删除成功");
        }else {
            map.put("code","400");
            map.put("message","删除失败");
        }
        return map;
    }
}
