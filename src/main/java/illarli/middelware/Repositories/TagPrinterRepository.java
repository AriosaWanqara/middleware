package illarli.middelware.Repositories;

import illarli.middelware.Models.TagPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagPrinterRepository extends JpaRepository<TagPrinter, String> {
}