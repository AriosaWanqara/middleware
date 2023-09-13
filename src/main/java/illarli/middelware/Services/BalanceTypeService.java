package illarli.middelware.Services;

import illarli.middelware.Models.BalanceType;
import illarli.middelware.Repositories.BalanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceTypeService {

    @Autowired
    private BalanceTypeRepository balanceTypeRepository;

    public List<BalanceType> getAll() {
        return this.balanceTypeRepository.findAll();
    }
}
