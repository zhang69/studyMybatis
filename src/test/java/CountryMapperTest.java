

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
    }
    @Test
    public void testInsertUser() {
        //注意事务提交即可
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = new SysUser();
        //设置jdbcType=time会报错，数据库里面的字段是timestamp而传递的数据是time，只有后面的部分。
        //useGeneratedKeys="true" keyProperty="id" 先访问数据库，获取数据库生成的主键
        sysUser.setCreateTime(new Date());
        sysUser.setHeadImg(new byte[]{1,2,3});
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
        List<SysRole> countries = mapper.selectRoleByUser(1l,1l);
        countries.forEach(x->System.out.println(x));
    }

    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = mapper.selectById(1001);
        sysUser.setUserInfo("zhangzhiyuan");
        int i = mapper.updateById(sysUser);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(i);
    }

    /**
     * mybatis mapper接口可以直接执行的原因是利用动态代理+sqlSession.selectList("selectAll") sql的id
     *
     */
    @Test
    public void testMultiParam() {
        //多个参数即使同名，也要使用@param来显示指定
        //类的属性取值要用类名加.的方式
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> sysRoles = mapper.selectRoleByUser(1L,null);
        sysRoles.forEach(x->System.out.println(x));
    }

    /**
     * 格式
     * <choose>
     *     <when test="">
     *
     *     </when>
     *     <when test="">
     *
     *     </when>
     *     <otherwise>
     *         1=2
     *     </otherwise>
     * </choose>
     *
     * 注意if 不支持else的形式
     */
    @Test
    public void testChoose() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> zhangzhiyuan = mapper.selectByNameOrInfo("zhangzhiyuan", null);
        zhangzhiyuan.forEach(x->System.out.println());
    }
    /**
     * <where>可以解决1=1的问题，自动剔除开头的and or</where>
     * <set>去除调结尾的逗号，还是要加上id=#{id},还</set>
     * <trim prefix="前缀包含" suffix="后缀包含" prefixOverrides="前缀过滤" suffixOverrides="后缀过滤"></trim>
     * <bind name = "" value = ""/> 组合concat函数功能，oracle只能双拼，mysql可以多拼的问题
     * <if test="_databaseId == 'mysql'"><if/>多数据库类型问题可以用,额外配置<databaseidProvidertype = "DB_VENDOR" />
     */
    /**
     * 测试Association 的，Association是写在resultmap里面的，resultmap可以extends父类，书写起来比较美观
     */
    @Test
    public void testAss() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectAssociation();
        sysUsers.forEach(x->System.out.println(x));
    }

    /**
     * 测试一对一关联
     * 一对一关联可以使用resultMap，本质上就是对查找得到的结果的一种映射而已
     * 使用resultMap需要注意的是防两个表里面的字段有重名的,使用別名，对应的resultmap里面也要注意
     */
    @Test
    public void testONE2ONE() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectUserAndRole();
        sysUsers.forEach(x->System.out.println(x));
    }

    /**
     * 首先注意有些方法会造成加载 equal toString clone hashcode 注意一下
     * 主要是配置fetchType="lazy"
     * 和<setting name="aggressiveLazyLoading" value="false"/>
     */
    @Test
    public void testAssSelect() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectAssociationBySelect();
        sysUsers.forEach(x->System.out.println(x.getUserName()));
    }

    @Test
    public void testSelectRoleByColAndAsso() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> sysRoles = mapper.selectRoleByUserId(1L);
        sysRoles.forEach(x->System.out.println(x));
    }

    /**
     * 默认开启mybatis一级缓存，flushCache="false",可以破坏
     * 一级缓存的作用，当调有方法和参数是一样的时候，获取的是第一次获取对象的引用，不再去数据库查
     */
    @Test
    public void testCached() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = mapper.selectById(1);
        sysUser.setUserName("bbbbbbbbbbb");
        SysUser sysUser1 = mapper.selectById(1);
        System.out.println(sysUser1);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SysUserMapper mapper1 = sqlSession1.getMapper(SysUserMapper.class);
        SysUser sysUser2 = mapper1.selectById(1);
        System.out.println(sysUser2);
    }

    /**
     * 无论单个还是集合，效果是一样对，都会受到影响
     */
    @Test
    public void testCachedAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectAll();
        sysUsers.forEach(x->System.out.println(x));
        sysUsers.get(0).setUserName("bbbbbbbbbbb");
        List<SysUser> sysUsers1 = mapper.selectAll();
        sysUsers1.forEach(x->System.out.println(x));
    }

    /**
     * 二级缓存 只需要加cache标签即可，但是必须有sqlSession.close方法执行，才可以将二级缓存刷新到mybatis里的hashmap中去
     * 这里实际上是有一个小问题的，如果有修改数据，仅仅修改而没有保存，在close的时候会保存进2级缓存，致使下一次查询的结果有错误
     * 避免没有必要对修改，如果不需要保存的话
     *
     * 二级缓存会出现脏数据的影响 ，这时候需要用到cache-ref 参照缓存
     * 但是参照缓存对于多表关联还是会有问题，因此需要慎用
     */
    @Test
    public void testCached2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        SysRole sysRole = mapper.selectRoleById(1L);
        sysRole.setRoleName("new rolename");
        SysRole sysRole1 = mapper.selectRoleById(1L);
        System.out.println(sysRole==sysRole1);
        sqlSession.close();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SysRoleMapper mapper1 = sqlSession1.getMapper(SysRoleMapper.class);
        SysRole sysRole2 = mapper1.selectRoleById(1L);
        SysRole sysRole3 = mapper1.selectRoleById(1L);
        System.out.println(sysRole2);
        System.out.println(sysRole3);
        System.out.println(sysRole2==sysRole3);
    }


}
