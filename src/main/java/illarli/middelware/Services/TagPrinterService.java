package illarli.middelware.Services;

import illarli.middelware.Models.TagPrinter;
import illarli.middelware.Repositories.TagPrinterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagPrinterService {

    @Autowired
    private TagPrinterRepository tagPrinterRepository;

    public TagPrinter save(TagPrinter tagPrinter) {
        this.tagPrinterRepository.deleteAll();
        return this.tagPrinterRepository.save(tagPrinter);
    }

    public boolean update(TagPrinter tagPrinter) {
        Optional<TagPrinter> ensureTaqPrinterExist = this.tagPrinterRepository.findById(tagPrinter.getId());
        if (ensureTaqPrinterExist.isPresent()) {
            this.tagPrinterRepository.save(tagPrinter);
            return true;
        } else {
            return false;
        }
    }

    public List<TagPrinter> getAll() {
        return this.tagPrinterRepository.findAll();
    }

    public boolean delete(String id) {
        Optional<TagPrinter> ensureTagPrinterExist = this.tagPrinterRepository.findById(id);
        if (ensureTagPrinterExist.isPresent()) {
            this.tagPrinterRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
