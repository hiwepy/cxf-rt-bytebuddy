package org.apache.cxf.endpoint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddy_Test {

	@Test
	public void test1() throws InstantiationException, IllegalAccessException {
		
		Class<?> dynamicType = new ByteBuddy()
				  .subclass(Object.class)
				  .method(ElementMatchers.named("toString"))
				  .intercept(FixedValue.value("Hello World!"))
				  .make()
				  .load(getClass().getClassLoader())
				  .getLoaded();
		
		assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
		
	}
	
	@Test
	public void testx() throws InstantiationException, IllegalAccessException {
		
		Class<? extends java.util.function.Function> dynamicType = new ByteBuddy()
				  .subclass(java.util.function.Function.class)
				  .method(ElementMatchers.named("apply"))
				  .intercept(MethodDelegation.to(new GreetingInterceptor()))
				  .make()
				  .load(getClass().getClassLoader())
				  .getLoaded();
				assertThat((String) dynamicType.newInstance().apply("Byte Buddy"), is("Hello from Byte Buddy"));
				
	}
	
	/*@Test
	public void test2() throws InstantiationException, IllegalAccessException {
		MemoryDatabase loggingDatabase = new ByteBuddy()
				  .subclass(MemoryDatabase.class)
				  .method(named("load")).intercept(MethodDelegation.to(LoggerInterceptor.class))
				  .make()
				  .load(getClass().getClassLoader())
				  .getLoaded()
				  .newInstance();
	}*/
	


	
	
}
