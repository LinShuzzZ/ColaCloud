package org.cola.colacloud;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cola.colacloud.Dao.UserMapper;
import org.cola.colacloud.POJO.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ColacloudApplicationTests {

	@Autowired
	public UserMapper userMapper;

	@Test
	void contextLoads() {

		Map<String,Object> map = new HashMap<>();
        map.put("username","zs");

        List<User> users = userMapper.selectByMap(map);

		for (User user : users) {
			System.out.println(user);
		}

	}

}
