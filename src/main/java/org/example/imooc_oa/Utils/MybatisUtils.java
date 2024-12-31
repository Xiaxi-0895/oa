package org.example.imooc_oa.Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

public class MybatisUtils {
    private static final SqlSessionFactory sqlSessionFactory;
    static {
        Reader reader=null;
        try {
            reader= Resources.getResourceAsReader("Mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public Object executeSql(Function<SqlSession,Object> function) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return function.apply(sqlSession);
        }
    }

}
