/**
 * Created by root on 4/13/17.
 */
package SparkStreaming;

import edu.thss.platform.pas.userfunc.UserFunction;
import java.net.URL;
import java.net.URLClassLoader;
/*import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;*/

public class LoadJar {
    private static UserFunction impl = null;

    /*public static UserIface loadUserFunction(String classPath, String jarPath){
        return loadJar1(classPath, jarPath);
    }
    public static void main(String[] args) {
        String classPath = "UserFunction";// Jar中的所需要加载的类的类名
        String jarPath = "file:///Users/vince/project/pas/src/main/resources/UserFunction.jar";// jar所在的文件的URL

        impl = loadJar1(classPath, jarPath);
        //loadClass(classPath);

        JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("test").setMaster("local[*]"));

        int NUM_SAMPLES = 1000;
        List<Integer> l = new ArrayList<>(NUM_SAMPLES);
        for (int i = 0; i < NUM_SAMPLES; i++) {
            l.add(i);
        }

        long count = sc.parallelize(l).filter(i -> {
            double x = Math.random();
            double y = Math.random();

            return impl.square(x) + impl.square(y) < 1;
        }).count();
        System.out.println("Pi is roughly " + 4.0 * count / NUM_SAMPLES);

        sc.close();
    }*/

    public static UserFunction loadJar1(String classPath, String jarPath) {
        ClassLoader cl;
        UserFunction impl = null;
        try {
            // 从Jar文件得到一个Class加载器
            cl = new URLClassLoader(new URL[]{new URL(jarPath)});
            // 从加载器中加载Class
            System.out.println("jarp: " + jarPath);
            System.out.println("clap: " + classPath);
            UserFunction tmp;
            Class<?> c = cl.loadClass(classPath);
            // 从Class中实例出一个对象
            impl = (UserFunction) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return impl;
    }

    public static void loadClass(String classPath){
        try {
            UserFunction impl2 = (UserFunction) Class.forName(classPath)
                    .newInstance();
            //System.out.println(impl2.square(2));
        } catch (Exception e) {
            System.out.println("非系统加载器加载的JAR,不能通过Class.forName使用");
        }
    }
}
