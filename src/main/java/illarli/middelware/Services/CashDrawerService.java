package illarli.middelware.Services;

import illarli.middelware.Models.CashDrawer;
import illarli.middelware.Repositories.CashDrawerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashDrawerService {

    @Autowired
    private CashDrawerRepository cashDrawerRepository;


    public List<CashDrawer> getAll() {
        return this.cashDrawerRepository.findAll();
    }

    public CashDrawer add(CashDrawer cashDrawer) {
        this.cashDrawerRepository.deleteAll();
        return this.cashDrawerRepository.save(cashDrawer);
    }

    public boolean update(CashDrawer cashDrawer) {
        Optional<CashDrawer> ensureCashDrawerExist = this.cashDrawerRepository.findById(cashDrawer.getId());
        if (ensureCashDrawerExist.isPresent()) {
            this.cashDrawerRepository.save(cashDrawer);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String id) {
        Optional<CashDrawer> ensureCashDrawerExist = this.cashDrawerRepository.findById(id);
        if (ensureCashDrawerExist.isPresent()) {
            this.cashDrawerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
