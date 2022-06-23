package org.cola.colacloud.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.cola.colacloud.POJO.User;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User>
{
    
    
}
