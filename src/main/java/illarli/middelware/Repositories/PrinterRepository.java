package illarli.middelware.Repositories;

import illarli.middelware.Models.Printers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrinterRepository extends JpaRepository<Printers, String> {
    Optional<Printers> findByDocumentTypes_Id(Long id);
}
