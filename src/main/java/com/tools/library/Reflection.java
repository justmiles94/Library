package com.tools.library;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class Reflection {

	Map<String, String> map = new HashMap<String, String>();

	public void run() {
		Class<Reflection> thisClass = (Class<Reflection>) this.getClass();
		classInfo(thisClass);
		is(thisClass);
		AnnotatedType[] AnnotatedType = thisClass.getAnnotatedInterfaces();
		Annotation[] getAnnotations = thisClass.getAnnotations();
		Annotation[] getDeclaredAnnotations = thisClass.getDeclaredAnnotations();
		Class<?>[] getClasses = thisClass.getClasses();
		Class<?>[] getInterfaces = thisClass.getInterfaces();
		Class<?>[] getDeclaredClasses = thisClass.getDeclaredClasses();
		Class<?> getComponentType = thisClass.getComponentType();
		Class<?> getDeclaringClass = thisClass.getDeclaringClass();
		Class<?> getEnclosingClass = thisClass.getEnclosingClass();
		Constructor<?> getEnclosingConstructor = thisClass.getEnclosingConstructor();
		Constructor<?>[] getConstructors = thisClass.getConstructors();
		Constructor<?>[] getDeclaredConstructors = thisClass.getDeclaredConstructors();
		Field[] getDeclaredFields = thisClass.getDeclaredFields();
		Field[] getFields = thisClass.getFields();
		Method[] getDeclaredMethods = thisClass.getDeclaredMethods();
		Method[] getMethods = thisClass.getMethods();
		Method getEnclosingMethod = thisClass.getEnclosingMethod();
		Reflection[] getEnumConstants = thisClass.getEnumConstants();
		Type[] getGenericInterfaces = thisClass.getGenericInterfaces();
		Package getPackage = thisClass.getPackage();
		ProtectionDomain getProtectionDomain = thisClass.getProtectionDomain();
		Object[] getSigners = thisClass.getSigners();
		TypeVariable<?>[] getTypeParameters = thisClass.getTypeParameters();
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}

	public void is(Class selection) {
		map.put("isArray", String.valueOf(selection.isArray()));
		map.put("isAnnotation", String.valueOf(selection.isAnnotation()));
		map.put("isAnnotationPresent", String.valueOf(selection.isAnnotationPresent(Annotation.class)));
		map.put("isAnonymousClass", String.valueOf(selection.isAnonymousClass()));
		map.put("isAssignableFrom", String.valueOf(selection.isAssignableFrom(selection)));
		map.put("isEnum", String.valueOf(selection.isEnum()));
		map.put("isInstance", String.valueOf(selection.isInstance(selection)));
		map.put("isInterface", String.valueOf(selection.isInterface()));
		map.put("isLocalClass", String.valueOf(selection.isLocalClass()));
		map.put("isMemberClass", String.valueOf(selection.isMemberClass()));
		map.put("isPrimitive", String.valueOf(selection.isPrimitive()));
		map.put("isSynthetic", String.valueOf(selection.isSynthetic()));
	}

	public void classInfo(Class getClass) {
		map.put("Class-getName", getClass.getName());
		map.put("Class-getSimpleName", getClass.getSimpleName());
		map.put("Class-getCanonicalName", getClass.getCanonicalName());
		map.put("Class-getTypeName", getClass.getTypeName());
		map.put("Class-toGenericString", getClass.toGenericString());
		map.put("Class-toString", getClass.toString());
		map.put("modifiers", String.valueOf(getClass.getModifiers()));
	}

}
