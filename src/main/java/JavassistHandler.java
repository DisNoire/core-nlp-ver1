import javassist.*;

public class JavassistHandler {

    private static ClassPool pool = ClassPool.getDefault();

    public static void makeClass(String className){
        pool.makeClass(className);
    }

    public static void makeMethod(String className, String methodName){
        try {
            pool.get(className).addMethod(CtNewMethod.make("public void " +
                    methodName + "(){}", pool.get(className)));
        } catch (CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addField(String className, String fieldName, String fieldValue){
        try {
            pool.get(className).addField(CtField.make("private String " + fieldName
                    + " = \"" + fieldValue + "\";", pool.get(className)));
        } catch (CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}
