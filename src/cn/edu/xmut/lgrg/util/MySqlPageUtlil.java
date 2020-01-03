package cn.edu.xmut.lgrg.util;

import cn.edu.xmut.lgrg.annotation.ZnSqlField;
import cn.edu.xmut.lgrg.entity.PageData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/1 21:34
 * @Description:
 *
 * 用于jdbc的分页，页数从0开始
 */
public class MySqlPageUtlil {
    String baseSql;
    ResultSet resultSet;
    List<PageData> resultList;
    boolean resultSetIsNew;
    Integer pageSize = 10;
    Integer pageIndex = 0;
    Integer total = 0;
    Integer totalPage = 0;
    String[] arr;

    public MySqlPageUtlil(String baseSql, PageData params,String... str) throws Exception {
        this.baseSql = baseSql;
        String pageSizeStr = params.getString("pageSize");
        String pageIndexStr = params.getString("pageIndex");
        try {
            pageIndex = Integer.valueOf(pageIndexStr);
            if (pageIndex < 0) {
                pageIndex = 0;
            }
        } catch (Exception e) {
        }
        try {
            pageSize = Integer.valueOf(pageSizeStr);
            if (pageSize <= 0) {
                pageSize = 1;
            }
        } catch (Exception e) {
        }
        arr = str;
        init();
    }

    public MySqlPageUtlil(String baseSql, Integer pageIndex, Integer pageSize,String... str) throws Exception {
        if (pageIndex < 0) {
            pageIndex = 0;
        }
        if (pageSize <= 0) {
            pageSize = 1;
        }
        this.baseSql = baseSql;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        arr = str;
        init();
    }

    public MySqlPageUtlil(String baseSql) throws Exception {
        this.baseSql = baseSql;
        init();
    }

    public MySqlPageUtlil(String baseSql,Integer pageSize) throws Exception {
        this.baseSql = baseSql;
        this.pageSize = pageSize;
        init();
    }

    public void init() throws Exception {
        try {
            String countSql = "select count(*) from (" + baseSql + ") as t";
            Connection connection = MySqlUtil.getCon();
            PreparedStatement pre = connection.prepareStatement(countSql);
            for (int i = 0; i < arr.length; i++) {
                pre.setString(i+1,arr[i]);
            }
            resultSet = pre.executeQuery();
            if(resultSet.next()){
                total = resultSet.getInt(1);
                //计算总页数
                totalPage = total/pageSize;
                if(total%pageSize != 0){
                    totalPage++;
                }
                if(totalPage-1<pageIndex){
                    pageIndex = totalPage-1;
                }

                //查询数量
                String sql = baseSql + " limit "+pageIndex*pageSize + ","+pageSize;
                pre = connection.prepareStatement(sql);
                for (int i = 0; i < arr.length; i++) {
                    pre.setString(i+1,arr[i]);
                }

                resultSet = pre.executeQuery();
                ResultSetMetaData rsmd = resultSet.getMetaData();
                Integer columnCount=rsmd.getColumnCount();
                List<String> columnNameList = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    columnNameList.add(rsmd.getColumnName(i));
                }
                resultList = new ArrayList<>();
                while (resultSet.next()){
                    PageData data = new PageData();
                    for (int i = 0; i < columnNameList.size(); i++) {
                        String columnName = columnNameList.get(i);
                        data.put(columnName,resultSet.getString(columnName));
                    }
                    resultList.add(data);
                }
                return;
            }
            throw new Exception("初始化异常~");
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("初始化异常，请联系管理员~");
        }
    }

    public String getBaseSql() {
        return baseSql;
    }

    public List<PageData> getResultList() {
        return resultList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public <T> List<T> getArray( Class<T> clazz) throws Exception {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++) {
            PageData result = resultList.get(i);
            Object ob = clazz.newInstance();
            Field fieldArr[] = clazz.getDeclaredFields();
            for (int j = 0; j < fieldArr.length; j++) {
                Field field = fieldArr[j];
                field.setAccessible(true);
                ZnSqlField znSqlField = field.getAnnotation(ZnSqlField.class);
                String sqlFieldName = znSqlField.name();
                String sqlFieldValue = result.getString(sqlFieldName);
                if (!StringUtil.isNull(sqlFieldValue)) {
                    field.set(ob, sqlFieldValue);
                }
            }
            list.add((T) ob);
        }
        return list;
    }
}
