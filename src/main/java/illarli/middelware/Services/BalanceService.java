package illarli.middelware.Services;

import illarli.middelware.Models.Balance;
import illarli.middelware.Models.BalanceLibraryRepository;
import illarli.middelware.Repositories.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public List<String> getComList(BalanceLibraryRepository balanceLibraryRepository) {
        return balanceLibraryRepository.getComList();
    }

    public Balance add(Balance balance) {
        balanceRepository.deleteAll();
        return balanceRepository.save(balance);
    }

    public boolean delete(String id) {
        Optional<Balance> ensureBalanceExist = balanceRepository.findById(id);
        if (ensureBalanceExist.isPresent()) {
            this.balanceRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Balance> getAll() {
        return balanceRepository.findAll();
    }
}
