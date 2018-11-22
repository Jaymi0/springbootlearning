/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.oauth2.controller 
 * @author: 502774066   
 * @date: Nov 21, 2018 1:30:10 PM 
 */

package com.jm.oauth2.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * @ClassName: UserController 
 * @Description: TODO
 * @author: 502774066
 * @date: Nov 21, 2018 1:30:10 PM  
 */
@RestController
public class UserController {

	@RequestMapping("/user")
	public Map<String, Object> user(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", principal.getName());
		return map;
	}
	
}
