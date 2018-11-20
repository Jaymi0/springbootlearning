package com.jm.springbootjdbc;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jm.springbootjdbc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootJdbcApplication.class)
public class SpringbootJdbcApplicationTests {
	
	@Autowired
	private UserService userService;
	private static Logger logger = Logger.getLogger(SpringbootJdbcApplicationTests.class);
	
	// prepare clear data
	@Before
	public void clearData() {
		userService.deleteAllUsers();
	}

	@Test
	public void contextLoads() {
		
		// insert data
		userService.insertUser("zhangsan", 12);
		userService.insertUser("lisi", 13);
		userService.insertUser("wangwu", 124);
		userService.insertUser("zhaoliu", 15);
		
		Assert.assertEquals(4, userService.getAllUsers().intValue());
		
		userService.deleteByName("zhangsan");
		
		assertEquals(3, userService.getAllUsers().intValue());
		
		
	}
	
	/** 
	* @Title: testLog 
	* @Description: TODO  void
	* @author 502774066
	* @date Nov 19, 20185:49:10 PM
	*/
	@Test
	public void testLog() {
		logger.info("info..");
		logger.debug("debug..");
		logger.error("error..");
	}

}
