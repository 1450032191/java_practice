package cn.edu.xmut.lgrg.util;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2019/12/31 10:08
 * @Description:
 */
public class ResultSetUtil {
    public static <T> List<T> getArray(ResultSet resultSet, Class<T> clazz) throws Exception {
        ArrayList<T> list = new ArrayList<>();
        while (resultSet != null && resultSet.next()) {
            Object ob = clazz.newInstance();

            Field fieldArr[] = clazz.getDeclaredFields();
            for (int i = 0; i < fieldArr.length; i++) {
                Field field = fieldArr[i];
                field.setAccessible(true);
                ZnSqlField znSqlField = field.getAnnotation(ZnSqlField.class);
                if(znSqlField != null){
                    String sqlFieldName = znSqlField.name();
                    try {
                        String sqlFieldValue = resultSet.getString(sqlFieldName);
                        if (!StringUtil.isNull(sqlFieldValue)) {
<<<<<<< HEAD
                            if(ResultSetUtil.isDouble(field)){
                                field.set(ob, Double.valueOf(sqlFieldValue));
                            }else {
                                field.set(ob, sqlFieldValue);
                            }
                        }
                    }catch (Exception e){}

=======
                            if (ResultSetUtil.isDouble(field)) {
                                field.set(ob, Double.valueOf(sqlFieldValue));
                            } else {
                                field.set(ob, sqlFieldValue);
                            }
                        }
                    } catch (Exception e) {
                    }
>>>>>>> e5b10f4853fac572aae3f3bf8a300c2285e666d2
                }
            }
            list.add((T) ob);
        }
        return list;
    }

    public static boolean isString(Field field) {
        return ("class java.lang.String".equals(field.getGenericType().toString()));
    }

    public static boolean isDouble(Field field) {
        return ("class java.lang.Double".equals(field.getGenericType().toString()));
    }
}
