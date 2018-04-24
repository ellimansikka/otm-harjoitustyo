package datingsimulator.domain;

import java.util.ArrayList;
import java.util.List;
import datingsimulator.dao.ResultDao;

public class FakeResultDao implements ResultDao {

    List<Result> results;

    public FakeResultDao() {
        results = new ArrayList<>();
    }

    @Override
    public List<Result> getAll() {
        return results;
    }

    @Override
    public Result create(Result result) {
        results.add(result);
        return result;
    }

}
