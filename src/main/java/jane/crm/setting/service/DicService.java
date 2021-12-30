package jane.crm.setting.service;

import jane.crm.setting.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicService {

    Map<String, List<DicValue>> getDicValues();
}
