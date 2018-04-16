package datingsimulator.dao;

import datingsimulator.domain.Result;
import java.util.List;

public interface ResultDao {

    Result create(Result result) throws Exception;

    List<Result> getAll();

}
