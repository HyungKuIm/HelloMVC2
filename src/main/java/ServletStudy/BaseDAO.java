package ServletStudy;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    public final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public final String USER = "sora2";
    public final String PWD = "1234";

    protected Connection con;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    private Class entityClass;

    public BaseDAO() {
        Type genericType = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected Connection getConn() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void close(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i+1, params[i]);
                }
        }
    }

    protected int executeUpdate(String sql, Object... params)  {
        boolean inserFlag = false;
        inserFlag = sql.trim().toLowerCase().startsWith("insert into ");
        try {
            con = getConn();
            if (inserFlag) {
                psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                psmt = con.prepareStatement(sql);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();
            if (inserFlag) {
                if (rs.next()) {
                    return ((Long)rs.getLong(1)).intValue();
                }
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, con);
        }
        return 0;
    }

    private void setValue(Object obj, String property, Object propertyValue) {
        Class<?> clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            field.setAccessible(true);
            if (propertyValue instanceof BigDecimal) {
                field.set(obj, ((BigDecimal)propertyValue).longValue());
            } else {
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected Object[] executeComplexQuery(String sql, Object... params)  {
        try {
            con = getConn();
            psmt = con.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];

            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i+1);
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, con);
        }
        return null;
    }

    protected T load(String sql, Object... params)  {
        try {
            con = getConn();
            psmt = con.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T entity = (T)entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnLabel(i+1);
                    Object columnValue = rs.getObject(i+1);
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, con);
        }
        return null;
    }

    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            con = getConn();
            psmt = con.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                T entity = (T)entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnLabel(i+1).toLowerCase();
                    Object columnValue = rs.getObject(i+1);
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, con);
        }
        return list;
    }
}
