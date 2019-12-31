package cn.edu.xmut.lgrg.util;

import cn.edu.xmut.lgrg.annotation.ZnService;
import cn.edu.xmut.lgrg.dao.SysUserDao;
import cn.edu.xmut.lgrg.dao.impl.SysUserImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/31 08:29
 * @Description:
 */
public class BeanUtil {
    static List<Object> objectList;
    static {
        List<Class<?>> classes = ClasstUtils.getClasses("cn.edu.xmut.lgrg.dao");
        objectList = new ArrayList<>();
        for (Class<?> class1 : classes) {
            System.out.println(class1);
            ZnService znService = class1.getAnnotation(ZnService.class);
            if(class1.isAnnotationPresent(ZnService.class)){
                try {
                    Object ob = class1.newInstance();
                    objectList.add(ob);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static <T> T getInstance(Class<T> var1){
        for (int i = 0; i < objectList.size(); i++) {
            Object object = objectList.get(i);
            if(var1!=null&&var1.isInstance(object)){
                return (T) object;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(getInstance(SysUserDao.class));
    }
}
