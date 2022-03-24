package com.winston.cache.domin;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//Serializable接口实现book对象的序列化 这样子才能存到redis数据库中
@Component
@Data
public class Book implements Serializable {
    private String bookName;
    private Integer id;
}
