/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.springbootjdbc.service 
 * @author: 502774066   
 * @date: Nov 19, 2018 3:00:09 PM 
 */

package com.jm.springbootjdbc.service;

 /** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 19, 2018 3:00:09 PM  
 */
public interface UserService {

	void insertUser(String name, Integer age);
	
	void deleteAllUsers();
	
	void deleteByName(String name);
	
	Integer getAllUsers();
	
}
