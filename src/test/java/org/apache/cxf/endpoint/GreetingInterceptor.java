package org.apache.cxf.endpoint;

public class GreetingInterceptor {
	
	public Object greet(Object argument) {
		return "Hello from " + argument;
	}
	
}