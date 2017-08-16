package com.orasi.utils;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class JavaUtilities {

	public static boolean isValid(Object obj){
		boolean isValid = false;
		
		if(obj == null){
			return isValid;
		}else{
			isValid = true;
		}
		
		if(isValid){
			if(obj instanceof String){
				if(StringUtils.isEmpty(((String) obj))){
					return false;
				}else{
					return true;
				}
			}else if(obj instanceof Collection<?>){
				if(((Collection<?>) obj).isEmpty()){
					return false;
				}else{
					return true;
				}
			}else if(obj instanceof Map<?, ?>){
				if(((Map<?,?>) obj).isEmpty()){
					return false;
				}else{
					return true;
				}
			}
		}
		
		return isValid;
	}
}