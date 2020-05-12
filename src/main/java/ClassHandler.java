import java.util.Map;

public class ClassHandler {
    private String className;
    private Map<String,String> attributes;
    private Map<String,String> methods;

    public ClassHandler(String className, Map<String,String> attributes, Map<String,String> methods) {
        this.className = className;
        this.attributes = attributes;
        this.methods = methods;
    }

    public void makeClass(){
        JavassistHandler.makeClass(className);
        if (methods != null) {
            for (String methodName : methods.keySet()) {
                JavassistHandler.makeMethod(className, methodName, methods.get(methodName));
            }
        }
        JavassistHandler.writeFile(className);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String,String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String,String> attributes) {
        this.attributes = attributes;
    }

    public Map<String,String> getMethods() {
        return methods;
    }

    public void setMethods(Map<String,String> methods) {
        this.methods = methods;
    }
}
