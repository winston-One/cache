package com.winston.cache.service;

import com.winston.cache.domin.Book;

import java.util.List;

public interface BookService {
    public boolean save(Book book);
    public boolean update(Book book);
    public boolean delete(Integer id);
    public List<Book> getAll(Book book);
    public Book getById(Integer id);
}
