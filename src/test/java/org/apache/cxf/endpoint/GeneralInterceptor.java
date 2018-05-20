package org.apache.cxf.endpoint;

import java.lang.reflect.Method;

import net.bytebuddy.asm.Advice.AllArguments;
import net.bytebuddy.asm.Advice.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

public class GeneralInterceptor {
	
	@RuntimeType
	public Object intercept(@AllArguments Object[] allArguments, @Origin Method method) {
		// intercept any method of any signature
		
		return null;
	}
}