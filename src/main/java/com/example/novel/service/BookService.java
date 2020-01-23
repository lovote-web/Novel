package com.example.novel.service;

import com.example.novel.po.Book;

import java.util.List;

public interface BookService {
    public int joinBookshelf(Long id,Long nId);

    public boolean isExist(Long id,Long nId);

    public List<Book> getBookshelf(Long id);

    public int deleteBook(Long id,Long nId);
}
