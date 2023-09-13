package illarli.middelware.Models;

import java.util.List;
import java.util.function.Function;

public interface BalanceLibraryRepository {

    List<String> getComList();

    void open(Function<String, String> send, Balance balance);
}
