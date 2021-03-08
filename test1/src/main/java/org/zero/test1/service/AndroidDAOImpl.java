package org.zero.test1.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.zero.test1.dao.AndroidDAO;
import org.zero.test1.vo.Users;

public class AndroidDAOImpl implements AndroidDAO {
    @Autowired
    SqlSession sqlSession;

    @Override
    public String confirmUser(Users user) {
        AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
        String enabled = mapper.confirmUser(user);
        return enabled;
    }
}
