package g_annotations_with_java_reflection_e1_initializing_application.app.http;

import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerClass;
import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService() {
        System.out.println("Service successfully registered");
    }
}
