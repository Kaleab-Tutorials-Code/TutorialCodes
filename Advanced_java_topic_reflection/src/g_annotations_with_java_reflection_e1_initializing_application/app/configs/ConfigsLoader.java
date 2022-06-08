package g_annotations_with_java_reflection_e1_initializing_application.app.configs;

import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerClass;
import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {
	@InitializerMethod
	public void loadAllConfigs() {
		System.out.println("Loading all configuration files");
	}
}
