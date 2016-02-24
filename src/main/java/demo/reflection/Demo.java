package demo.reflection;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yaochao on 2016/2/24.
 */
public class Demo {

    private static final String username = "yaochao";

    private static final String password = "123456";

    /**
     * 利用反射操作<code>User</code>的公有属性username
     */
    @Test
    public void publicFieldTest() {
        try {
            Class c = Class.forName("demo.reflection.User");
            Object user = c.newInstance();
            Field field = c.getField("username");
            field.set(user, username);
            Assert.assertEquals("获取公有属性失败！", username, field.get(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用反射操作<code>User</code>的私有属性password
     */
    @Test
    public void privateFieldTest() {
        try {
            Class c = Class.forName("demo.reflection.User");
            Object user = c.newInstance();
            Field field = c.getDeclaredField("password");//很关键
            field.setAccessible(true);//很关键
            field.set(user, password);
            Assert.assertEquals("获取私有属性失败！", password, field.get(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用反射操作<code>User</code>的公有方法
     */
    @Test
    public void publicMethodTest() {
        try {
            Class c = Class.forName("demo.reflection.User");
            Object user = c.newInstance();
            Method setUsernameMethod = c.getMethod("setUsername", String.class);
            setUsernameMethod.invoke(user, username);
            Method getUsernameMethod = c.getMethod("getUsername");
            Assert.assertEquals("调用公有方法失败测试！", username, getUsernameMethod.invoke(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用反射操作<code>User</code>的私有方法
     */
    @Test
    public void privateMethodTest() {
        try {
            Class c = Class.forName("demo.reflection.User");
            Object user = c.newInstance();
            Method setPasswordMethod = c.getDeclaredMethod("setPassword", String.class);//很关键
            setPasswordMethod.setAccessible(true);//很关键
            setPasswordMethod.invoke(user, password);
            Method getPasswordMethod = c.getDeclaredMethod("getPassword");//很关键
            getPasswordMethod.setAccessible(true);//很关键
            Assert.assertEquals("调用公有方法失败测试！", password, getPasswordMethod.invoke(user));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
