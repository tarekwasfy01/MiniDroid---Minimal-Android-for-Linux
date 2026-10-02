public final class FrameworkSmoke {
    public static void main(String[] args) throws Exception {
        Class<?> application = Class.forName("android.app.Application");
        Object instance = application.getDeclaredConstructor().newInstance();
        System.out.println("MiniDroid framework OK: " + instance.getClass().getName());
    }
}
