package com.winston.cache.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winston.cache.domin.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {
}
