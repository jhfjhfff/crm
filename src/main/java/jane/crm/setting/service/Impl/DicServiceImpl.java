package jane.crm.setting.service.Impl;

import jane.crm.setting.dao.DicTypeDao;
import jane.crm.setting.dao.DicValueDao;
import jane.crm.setting.domain.DicType;
import jane.crm.setting.domain.DicValue;
import jane.crm.setting.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {

    @Resource
    private DicTypeDao dicTypeDao;

    @Resource
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getDicValues() {

        Map<String,List<DicValue>> map = new HashMap<>();

        //拿到所有的DicType
        List<DicType> dicTypeList = dicTypeDao.getTypes();

        //用一个map集合，把每一个DicType当key，把每个key对应的DicValue当值存入到Map<String, List<DicValue>>中
        for (DicType dicType : dicTypeList){
            String code = dicType.getCode();

            //根据DicType中的code去获取相应的数据
            List<DicValue> dicValueList = dicValueDao.getValues(code);

            map.put(code,dicValueList);
        }

        return map;
    }
}
