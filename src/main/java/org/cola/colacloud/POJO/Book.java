package org.cola.colacloud.POJO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable
{
    
    private Integer id;
    private String bookname;
    private Float bookprice;

}
