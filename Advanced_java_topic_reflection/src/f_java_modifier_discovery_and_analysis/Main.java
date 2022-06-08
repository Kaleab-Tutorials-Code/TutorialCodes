package f_java_modifier_discovery_and_analysis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import f_java_modifier_discovery_and_analysis.auction.Auction;
import f_java_modifier_discovery_and_analysis.auction.Bid;

//Note that: getModifiers gives you a number and each modifier are represented by a number i.e static = 8 and public = 1. lets say if we get 00001001 , the last one represent "public" and the other one represent static modifier
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        printClassModifiers(Auction.class);
        printClassModifiers(Bid.class);
        printClassModifiers(Bid.Builder.class);
        printClassModifiers(Class.forName("f_java_modifier_discovery_and_analysis.auction.Auction"));
        printClassModifiers(Class.forName("f_java_modifier_discovery_and_analysis.auction.Bid$Builder$BidImpl"));

        printMethodsModifiers(Auction.class.getDeclaredMethods());

        printFieldsModifiers(Auction.class.getDeclaredFields());
        printFieldsModifiers(Bid.class.getDeclaredFields());
    }

    public static void printFieldsModifiers(Field[] fields) {
        for (Field field : fields) {
            int modifier = field.getModifiers();

            System.out.println(String.format("Field \"%s\" access modifier is %s",
                    field.getName(),
                    getAccessModifierName(modifier)));

            if (Modifier.isVolatile(modifier)) {
                System.out.println("The field is volatile");
            }

            if (Modifier.isFinal(modifier)) {
                System.out.println("The field is final");
            }

            if (Modifier.isTransient(modifier)) {
                System.out.println("The field is transient and will not be serialized");
            }
            System.out.println();
        }
    }

    public static void printMethodsModifiers(Method[] methods) {
        for (Method method : methods) {
            int modifier = method.getModifiers();

            System.out.println(String.format("%s() access modifier is %s",
                    method.getName(),
                    getAccessModifierName(modifier)));

            if (Modifier.isSynchronized(modifier)) {
                System.out.println("The method is synchronized");
            } else {
                System.out.println("The method is not synchronized");
            }
            System.out.println();
        }
    }

    //This method used to check the modifier exist on a class.
    public static void printClassModifiers(Class<?> clazz) {
       //this integer value will represent all the modifier at this class level. so with helper classes we can print all the modifiers. i.e Modifier.isStatic will check if a number contain 1 at the 4th bit from the right
    	int modifier = clazz.getModifiers();

        System.out.println(String.format("Class %s access modifier is %s",
                clazz.getSimpleName(),
                getAccessModifierName(modifier)));

        if (Modifier.isAbstract(modifier)) {
            System.out.println("The class is abstract");
        }

        if (Modifier.isInterface(modifier)) {
            System.out.println("The class is an interface");
        }

        if (Modifier.isStatic(modifier)) {
            System.out.println("The class is static");
        }
    }

    private static String getAccessModifierName(int modifier) {
        if (Modifier.isPublic(modifier)) {
            return "public";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        } else if (Modifier.isProtected(modifier)) {
            return "protected";
        } else {
            return "package-private";
        }
    }

    public static void runAuction() {
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = Bid.builder()
                .withBidderName("Company1")
                .withPrice(10)
                .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .withBidderName("Company2")
                .withPrice(12)
                .build();
        auction.addBid(bid2);

        auction.stopAuction();

        System.out.println(auction.getAllBids());
        System.out.println("Highest bid :" + auction.getHighestBid().get());
    }
}
