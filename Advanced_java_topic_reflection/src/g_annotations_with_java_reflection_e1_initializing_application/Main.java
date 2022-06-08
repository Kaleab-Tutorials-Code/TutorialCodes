package g_annotations_with_java_reflection_e1_initializing_application;

/*
 *  MIT License
 *
 *  Copyright (c) 2020 Michael Pogrebinsky - Java Reflection - Master Class
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerClass;
import g_annotations_with_java_reflection_e1_initializing_application.annotations.InitializerMethod;

/**
 * Annotations - Application Initialization
 * https://www.udemy.com/course/java-reflection-master-class
 */

//What are we going to do here: 
//- I will practice creating my own annotations that can be discovered at runtime during reflection. 
//- I will learn how to find a particular annotation on any target. 
//- Automatically find classes in out application classpath. 
//- We combined all our knowledge and implemented a practical example of making initializing methods and classes using annotations.

//Here is the thing: 
// --> let's say I have an application. so before the app starts the following things may happens: 
	//-- Configurations will be loaded
	//-- we may connect the app two database 1 and database 2.
	//-- we may load data from cache. 
	//-- we may register our service. 
	//-- we may automatically save data when needed. This may be executed on a backgroud thread.

// So To do that: 
	//--> we created two annotations IntializeMethods and InitializeClass, so using this two annotation we can executed required methods to do the following.

public class Main {

    public static void main(String[] args) throws Throwable {
        initialize("app", "app.configs", "app.databases", "app.http");
    }

    //This method will execute every method in a class which are annotated with InitializerMethod annotation and they are a part of a class annotated with InitializerClass.
    public static void initialize(String... packageNames) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, IOException, URISyntaxException {
        List<Class<?>> classes = getAllClasses(packageNames);

        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(InitializerClass.class)) {
                continue;
            }

            List<Method> methods = getAllInitializingMethods(clazz);

            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method method : methods) {
                method.invoke(instance);
            }
        }
    }

    //This methods will give the list of methods that are annotated with IntializerMethod for a given class.
    private static List<Method> getAllInitializingMethods(Class<?> clazz) {
        List<Method> initializingMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(InitializerMethod.class)) {
                initializingMethods.add(method);
            }
        }
        return initializingMethods;
    }
    
    //WHAT STRATEGY I USED TO GET THE CLASSES. 
    /*
     * In java, classes are not available to the JVM until they are used in the code and loaded by the Class Loader. So to automatically discover all the classes in a package
     * we need to look for the files with the ".class" extension in the package path. The ".class" files contain the compiled Java class.
     * Here is the thing, for example when we run a java class with main method by passing the class path like this : java -cp "/home/additional_classes" Main. 
     * Here "/home/additional_classes" is the location of the main class. so i.e if we have a class Product inside app.data package then the path for this file become 
     * /home/additional_classes/app/data/Product.class. similary a class packaged inside a jar file can be executed like this: java -cp "dependency.jar" Main and i.e if there
     * is a class Product inside app.data package then it can refered as dependency.jar:app/data/Product.class. 
     * So for our case to get the location of our classes we use getResources method to get the location where classes located and we change package name i.e app.data to app/data
     * and concatenate which give as the location of the class.
     */

    //This method will give me all the classes which are a part of a package.
    public static List<Class<?>> getAllClasses(String... packageNames) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for (String packageName : packageNames) {
            //you just change i.e app.data to app/data. 
        	String packageRelativePath = packageName.replace('.', '/');

           //This will give you the whole path to access this relative path.
        	//based on my understanding at this point: relative path is the location of the class relative to location of main class.
        	URI packageUri = Main.class.getResource(packageRelativePath).toURI();

            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
            } 
            //this else if will be used if the class is found inside a jar file. when i.e i run an app  using jar file of project then this will be used.
            else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());

                Path packageFullPathInJar = fileSystem.getPath(packageRelativePath);
                allClasses.addAll(getAllPackageClasses(packageFullPathInJar, packageName));

                fileSystem.close();
            }
        }
        return allClasses;
    }

    private static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName) throws IOException, ClassNotFoundException {

        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();

        for (Path filePath : files) {
            String fileName = filePath.getFileName().toString();

            //Here i make my own fix by adding g_annotations_with_java_reflection_e1_initializing_application. because my main Method is not located in src instead I have
            //subpackage with this name. so i.e if i try to create a class like Class.forName("app.AutoSaver") it does not work. I should type it like 
            //Class.forName("g_annotations_with_java_reflection_e1_initializing_application.app.AutoSaver") this will work. 
            if (fileName.endsWith(".class")) {
                String classFullName = packageName.isBlank() ?
                        "g_annotations_with_java_reflection_e1_initializing_application." + fileName.replaceFirst("\\.class$", "")
                        : "g_annotations_with_java_reflection_e1_initializing_application." +packageName + "." + fileName.replaceFirst("\\.class$", "");

                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }
        return classes;
    }
}

