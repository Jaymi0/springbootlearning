/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.oauth2.controller 
 * @author: 502774066   
 * @date: Nov 20, 2018 4:51:18 PM 
 */

package com.jm.oauth2.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: UserController 
 * @Description: 提供资源链接
 * @author: 502774066
 * @date: Nov 20, 2018 4:51:18 PM  
 */
@Controller
@RequestMapping("/users")
public class UserController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/me")
	public Principal me(Principal principal) {
		return principal;
	}

}
