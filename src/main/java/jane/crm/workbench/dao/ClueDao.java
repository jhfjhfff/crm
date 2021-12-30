package jane.crm.workbench.dao;

import jane.crm.setting.domain.User;
import jane.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueDao {
    List<User> getuList();

    int saveClue(Clue clue);

    int getClueCount();

    List<Clue> getClueList(Map<String, Integer> map);

    Clue queryClueById(String id);
}
