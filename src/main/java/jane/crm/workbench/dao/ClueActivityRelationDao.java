package jane.crm.workbench.dao;


import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.ClueActivityRelation;

import java.util.List;
import java.util.Map;

public interface ClueActivityRelationDao {

    List<Activity> showActivityByClueId(String clueId);

    int unbund(Map<String,String> map);

    int bund(ClueActivityRelation car);
}
