/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.springbootjdbc.controller 
 * @author: 502774066   
 * @date: Nov 19, 2018 3:33:14 PM 
 */

package com.jm.springbootjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jm.springbootjdbc.service.UserService;

import freemarker.cache.ClassTemplateLoader;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 19, 2018 3:33:14 PM  
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("host", "zhangsan");
		map.addAttribute("isAdmin", true);
		return "index";
	}

}
