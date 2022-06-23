package org.cola.colacloud.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.cola.colacloud.POJO.Book;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book>
{
    
}
