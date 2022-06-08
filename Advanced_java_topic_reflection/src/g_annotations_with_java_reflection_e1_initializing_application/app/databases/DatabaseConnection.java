package g_annotations_with_java_reflection_e1_initializing_application.app.databases;

import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerClass;
import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerMethod;

@InitializerClass
public class DatabaseConnection {
	
	@InitializerMethod
	public void connectToDatabase1() {
		System.out.println("Connecting to database 1");
	}
	
	@InitializerMethod
	public void connectToDatabase2() {
		System.out.println("Connecting to database 2");
	}

}
