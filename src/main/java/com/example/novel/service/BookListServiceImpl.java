package com.example.novel.service;

import com.example.novel.dao.BookListMapper;
import com.example.novel.po.BookList;
import com.example.novel.po.Novel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BookListServiceImpl implements BookListService {

    @Autowired
    BookListMapper bookListMapper;

    @Override
    public List<BookList> getAllBookList() {
        return bookListMapper.getAllBookList();
    }

    @Override
    public BookList getOneBookList(Long id) {
        return bookListMapper.getOneBookList(id);
    }

}
