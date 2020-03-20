package com.innove.authority.util;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: StringUtil.java
 * @Description: string处理工具类
 * @author paradise
 * @date 2019年8月30日
 */
public class StringUtil {
	
	private static String[] UPPER_CHARACTERS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	private static String[] LOWER_CHARACTERS = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	public static boolean isBlank(String s) {
		return s == null || ("").equals(s);
	}

	public static boolean isNotBlank(String s) {
		return s != null && !("").equals(s);
	}

	public static String businessCode(String head) {
		if(head == null || head.length() != 2) {
			head = "99";
		}
		String timeElement = DateTimeUtil.getFormatDateString("yyyyMMddHHmmssSSS", new Date());
		int a = RandomInRange(0,25);
		int b = RandomInRange(0,25);
		int r = RandomInRange(100000000,999999999);
		String code = head + UPPER_CHARACTERS[a] + timeElement + LOWER_CHARACTERS[b] + r;
		return code;
	}
	
	public static String randomString(int length) {
		String timeElement = DateTimeUtil.getFormatDateString("yyyyMMddHHmmss", new Date());
		int end = length - timeElement.length();
		if(end <= 0) {
			return timeElement;
		}else {
			int a = 0;
			StringBuffer r = new StringBuffer(timeElement);
			for(int i=0;i<end;i++) {
				a = RandomInRange(0,25);
				if(i%2 == 0) {
					r.append(UPPER_CHARACTERS[a]);
				}else {
					r.append(LOWER_CHARACTERS[a]);
				}
			}
			return r.toString();
		}
	}

	public static int RandomInRange(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}
	
    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    public static boolean contain(String str1,String str2){
    	if(str1==null||str2==null){
    		return false;
		}
		return str1.contains(str2);
	}

	public static boolean equals(String str1,String str2){
		if(str1==null||str2==null){
			return false;
		}
		return str1.equals(str2);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MD5("test"));
	}
	
}
