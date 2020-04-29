package com.spring5.twopattern.template;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public abstract class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> excuteQuery(String sql ,RowMapper<?> rowMapper,Object[] values){
        try {
            //获取连接
            Connection conn = this.getConnection();
            //创建语句集
            PreparedStatement pstm = this.createPrepargeStatement(conn,sql);
            //执行语句集
            ResultSet rs = this.excuteQuery(pstm,values);
            //处理结果集
            List<?> result = this.paresResultSet(rs,rowMapper);
            //关闭结果集
            this.closeResultSet(rs);
            //关闭语句集
            this.closeStatement(pstm);
            //关闭连接
            this.closeConnection(conn);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    private void closeStatement(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    private void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    public List<?> paresResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs,rowNum));
        }
        return result;
    }

    public ResultSet excuteQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i,values[i]);
        }
        return pstm.executeQuery();
    }

    public PreparedStatement createPrepargeStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
