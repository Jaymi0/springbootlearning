/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.springbootjdbc.service 
 * @author: 502774066   
 * @date: Nov 19, 2018 3:06:51 PM 
 */

package com.jm.springbootjdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 19, 2018 3:06:51 PM  
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.jm.springbootjdbc.service.UserService#insertUser(java.lang.String, java.lang.Integer)
	 */
	@Override
	public void insertUser(String name, Integer age) {
		jdbcTemplate.update(" insert into act_user(username, password) values(?, ?) ", name, age);
	}

	/* (non-Javadoc)
	 * @see com.jm.springbootjdbc.service.UserService#deleteAllUsers()
	 */
	@Override
	public void deleteAllUsers() {
		jdbcTemplate.update(" delete from act_user ");
	}

	/* (non-Javadoc)
	 * @see com.jm.springbootjdbc.service.UserService#deleteByName(java.lang.String)
	 */
	@Override
	public void deleteByName(String name) {
		jdbcTemplate.update(" delete from act_user where username = ? ", name);
	}

	/* (non-Javadoc)
	 * @see com.jm.springbootjdbc.service.UserService#getAllUsers()
	 */
	@Override
	public Integer getAllUsers() {
		return jdbcTemplate.queryForObject(" select count(1) from act_user ", Integer.class);
	}

}
