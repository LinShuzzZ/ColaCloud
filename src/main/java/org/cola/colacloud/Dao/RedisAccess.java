package org.cola.colacloud.Dao;

import javax.annotation.Resource;

import org.cola.colacloud.POJO.Book;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class RedisAccess {
    
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void setBook(Book book)
    {
        redisTemplate.opsForValue().set(book.getBookname(), book);
    }

    public Book getBook(String bookName)
    {
        return (Book)redisTemplate.opsForValue().get(bookName);
    }


}
