package org.apache.cxf.endpoint.jaxws;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.jws.WebParam;

import org.apache.cxf.endpoint.EndpointApi;
import org.apache.cxf.endpoint.jaxws.definition.SoapParam;
import org.junit.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ByteBuddy_JaxwsApi_Test {
	
	@Test
	public void testInstance() throws Exception{
		
		InvocationHandler handler = new EndpointApiInvocationHandler();
		
		DynamicType.Unloaded<?> dynamicType4 = new ByteBuddy()
				.with(new NamingStrategy.SuffixingRandom("Api212sd"))
				.subclass(EndpointApi.class)
				.defineMethod(name, returnType, modifierContributors)
				
				.make();
		 
		
		Object ctObject = new EndpointApiCtClassBuilder("org.apache.cxf.spring.boot.FirstCaseV2")
				.webService("get", "http://ws.cxf.com", "getxx")
				.newField(String.class, "uid", UUID.randomUUID().toString())
				.newMethod(String.class, "sayHello", new SoapParam(String.class, "text"))
				.newMethod(String.class, "sayHello2", new SoapParam(String.class, "text", WebParam.Mode.OUT))
				.toInstance(handler);
		
		
		
		
		
		Class clazz = ctObject.getClass();
		
		System.err.println("=========Type Annotations======================");
		for (Annotation element : clazz.getAnnotations()) {
			System.out.println(element.toString());
		}
		
		System.err.println("=========Fields======================");
		for (Field element : clazz.getDeclaredFields()) {
			System.out.println(element.getName());
			for (Annotation anno : element.getAnnotations()) {
				System.out.println(anno.toString());
			}
		}
		System.err.println("=========Methods======================");
		for (Method method : clazz.getDeclaredMethods()) {
			System.out.println(method.getName());
			System.err.println("=========Method Annotations======================");
			for (Annotation anno : method.getAnnotations()) {
				System.out.println(anno.toString());
			}
			System.err.println("=========Method Parameter Annotations======================");
			for (Annotation[] anno : method.getParameterAnnotations()) {
				System.out.println(anno[0].toString());
			}
		}
		System.err.println("=========sayHello======================");
		Method sayHello = clazz.getMethod("sayHello", String.class);
		sayHello.invoke(ctObject,  " hi Hello " );
		System.err.println("=========sayHello2======================");
		Method sayHello2 = clazz.getMethod("sayHello2", String.class);
		sayHello2.invoke(ctObject,  " hi Hello2 " );
	}

}
