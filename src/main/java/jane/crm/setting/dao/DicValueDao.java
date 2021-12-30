package jane.crm.setting.dao;

import jane.crm.setting.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getValues(String code);
}
