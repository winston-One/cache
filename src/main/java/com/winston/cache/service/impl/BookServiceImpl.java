package com.winston.cache.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.winston.cache.domin.Book;
import com.winston.cache.dao.BookDao;
import com.winston.cache.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    //private HashMap<Integer,Book> cache = new HashMap();

    @Override
    //设定缓存空间，执行之后会把id 这个参数所查询的数据放到这个存储空间的所属id的区域，
    //@Cacheable(value = "cacheSpace",key = "#id")
    /**
     * 默认是redis
     * 一旦这个id属性的数据被更新之后，缓存中的也必须更新，否则就不能从缓存中查找了
     * b系统修改数据之后 必须让其他的系统也知道 必须实现同步 实现刷新 5秒刷新一次 自定义设置
     */
    @CacheRefresh(refresh = 5)
    @Cached(name="book_",key = "#id",expire = 3600,timeUnit = TimeUnit.SECONDS)

    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }
//@Override 自定义缓存
//public Book getById(Integer id) {
//    Book book = cache.get(id);
//    if (book == null) {
//        Book queryBook = bookDao.selectById(id);
//        cache.put(id,queryBook);
//        return queryBook;
//    }
//    return cache.get(id);
//}

    @Override
    public boolean save(Book book) {
        return bookDao.insert(book) > 0;
    }

    @CacheUpdate(name = "book_",key = "#book.id",value = "#book")
    @Override
    public boolean update(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    @CacheInvalidate(name = "book_",key = "#id")
    public boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public List<Book> getAll(Book book) {
        return bookDao.selectList(null);
    }
}
