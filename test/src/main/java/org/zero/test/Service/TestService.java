package org.zero.test.Service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.test.dto.Test;
import org.zero.test.mapper.TestMapper;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public List<Test> getAll() throws Exception {
        return testMapper.getAll();
    }
}