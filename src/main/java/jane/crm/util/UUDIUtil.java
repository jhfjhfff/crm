package jane.crm.util;

import java.util.UUID;

/**
 * @author zy
 */
// 得到32位唯一值
public class UUDIUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
