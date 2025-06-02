package club.shengsheng;


import java.io.File;
import java.nio.file.Files;

/**
 * @author gongxuanzhangmelt@gmail.com
 **/
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.startsWith("tech.insight.ShengSheng")) {
            String classPath = System.getProperty("user.dir").concat("/加密.class");
            File file = new File(classPath);
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                // 减少一个 1
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (bytes[i] - 1);
                }
                // 返回生成的类
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                throw new ClassNotFoundException(name);
            }
        }
        // 其他类遵循双亲委派机制
        return super.findClass(name);
    }
}
