package com.zzy.study.mapper;

import com.zzy.study.dao.SysUserMapper;
import com.zzy.study.model.Country;
import com.zzy.study.model.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/4/4 17:04
 */
public class CountryMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> selectList = sqlSession.selectList("selectCountrys");
            for (Country country : selectList) {
                System.out.println(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<SysUser> selectList = sqlSession.selectList("selectById",1);
        selectList.forEach(x->System.out.println(x));
    }

}
