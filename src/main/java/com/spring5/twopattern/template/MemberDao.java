package com.spring5.twopattern.template;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class MemberDao extends JdbcTemplate {
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }
    public List<?> selectAll(){
        String sql = "select * from t_member";
        return super.excuteQuery(sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setAddr(rs.getString("addr"));
                return member;
            }
        },null);
    }
}
