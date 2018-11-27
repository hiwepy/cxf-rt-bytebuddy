package org.apache.cxf.endpoint.intercept;

public class GreetingInterceptor {
	
	public Object greet(Object argument) {
		return "Hello from " + argument;
	}
	
}