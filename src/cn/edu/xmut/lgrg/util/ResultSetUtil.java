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
                String sqlFieldName = znSqlField.name();
                String sqlFieldValue = resultSet.getString(sqlFieldName);
                if (!StringUtil.isNull(sqlFieldValue)) {
                    field.set(ob, sqlFieldValue);
                }
            }
            list.add((T) ob);
        }
        return list;
    }

    private static boolean isString(Field field) {
        return ("class java.lang.String".equals(field.getGenericType().toString()));
    }
}
