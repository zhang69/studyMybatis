

import com.zzy.study.dao.CountryMapper;
import com.zzy.study.dao.SysRoleMapper;
import com.zzy.study.dao.SysUserMapper;
import com.zzy.study.model.Country;
import com.zzy.study.model.SysRole;
import com.zzy.study.model.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.util.Date;
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

    @Test
    public void testALL() {
        //linux 下的mapper文件大小写敏感？？必须用全小写？
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<SysUser> selectList = sqlSession.selectList("selectAll");
        selectList.forEach(x->System.out.println(x));
    }

    @Test
    public void testMapperCountry() {
        //xml 里的namespace对应着的是mapper.java的相对路径
        //别名只对实体类有效，对mapper无效
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
        List<Country> countries = mapper.selectCountrys();
        countries.forEach(x->System.out.println(x));
    }    @Test
    public void testInsertUser() {
        //注意事务提交即可
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = new SysUser();
        //设置jdbcType=time会报错，数据库里面的字段是timestamp而传递的数据是time，只有后面的部分。
        //useGeneratedKeys="true" keyProperty="id" 先访问数据库，获取数据库生成的主键
        sysUser.setCreateTime(new Date());
        sysUser.setHeadImg(new byte[]{1,2,3});
        sysUser.setUserEmail("1@1");
        sysUser.setUserName("zhangzhiyuan");
        sysUser.setUserInfo("zzy");
        sysUser.setUserPassword("helloworld");
        int i = mapper.insertUser(sysUser);
        System.out.println(i);
        System.out.println(sysUser.getId());
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testTableUnion() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> countries = mapper.selectRoleByUser();
        countries.forEach(x->System.out.println(x));
    }

}
