import com.zzy.study.model.Enabled;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/4/11 11:39
 */
public class enumTest {
    public static void main(String[] args) {
        Enabled enabled = Enabled.ENABLE;
        System.out.println(enabled);
        System.out.println(enabled.getValues());
        //toString 的方法是枚举类的实例名称
        System.out.println(Enabled.valueOf("ENABLE"));
    }
}
