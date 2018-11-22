/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.jm.oauth2.utils 
 * @author: 502774066   
 * @date: Nov 22, 2018 9:58:42 AM 
 */

package com.jm.oauth2.utils;

 /** 
 * @ClassName: ArraySortUtil 
 * @Description: use the thread to sort the arrays
 * @author: 502774066
 * @date: Nov 22, 2018 9:58:42 AM  
 */
public class ArraySortUtil implements Runnable {
	private Integer num;

	/** 
	 * @Title:ArraySortUtil
	 * @Description:TODO 
	 * @param num 
	 */ 
	public ArraySortUtil(Integer num) {
		this.num = num;
	}
	
	public static void main(String[] args) {
		int[] nums = {11,44,22,55,33,77,99};
		for (int i = 0; i < nums.length; i++) {
			new Thread(new ArraySortUtil(nums[i])).start();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(num);
			System.out.println(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
