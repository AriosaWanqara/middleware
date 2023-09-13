package illarli.middelware.Repositories;

import illarli.middelware.Models.CashDrawer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashDrawerRepository extends JpaRepository<CashDrawer, String> {
}