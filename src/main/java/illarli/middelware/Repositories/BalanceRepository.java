package illarli.middelware.Repositories;

import illarli.middelware.Models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, String> {
}