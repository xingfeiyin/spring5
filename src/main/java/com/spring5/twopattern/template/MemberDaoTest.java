package com.spring5.twopattern.template;

import java.util.List;

/**
 * @author yinxf
 * @date 2020-04-28
 */
public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }
}
