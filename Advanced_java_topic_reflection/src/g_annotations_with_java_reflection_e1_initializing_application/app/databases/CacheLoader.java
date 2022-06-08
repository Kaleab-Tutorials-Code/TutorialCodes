package g_annotations_with_java_reflection_e1_initializing_application.app.databases;

import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerClass;
import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerMethod;

@InitializerClass
public class CacheLoader {
	
	@InitializerMethod
	public void loadCache() {
		System.out.println("Loading data from cache");
	}
	
	public void reloadeCache() {
		System.out.println("Reload cache");
	}

}
