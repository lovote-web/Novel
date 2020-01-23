package com.example.novel.service;

import com.example.novel.po.BookList;

import java.util.List;

public interface BookListService {

    public List<BookList> getAllBookList();

    public BookList getOneBookList(Long id);
}
