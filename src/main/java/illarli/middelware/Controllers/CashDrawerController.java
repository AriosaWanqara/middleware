package illarli.middelware.Controllers;

import illarli.middelware.Infrastructure.Print.EscposCoffee;
import illarli.middelware.Models.CashDrawer;
import illarli.middelware.Repositories.PrinterRepository;
import illarli.middelware.Resolvers.CashDrawerDTO;
import illarli.middelware.Services.CashDrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cash-drawer")
public class CashDrawerController {
    @Autowired
    private CashDrawerService cashDrawerService;

    @Autowired
    private PrinterRepository printerRepository;

    @GetMapping("/list")
    public ResponseEntity<List<CashDrawer>> getAll() {
        return new ResponseEntity<>(this.cashDrawerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CashDrawerDTO cashDrawer) {
        CashDrawer cashDrawerEntity = cashDrawer.getCashDrawerFromCashDrawerDTO();
        this.cashDrawerService.add(cashDrawerEntity);
        return new ResponseEntity<>(this.cashDrawerService.add(cashDrawerEntity), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody CashDrawer cashDrawer) {
        if (!this.cashDrawerService.update(cashDrawer)) {
            return new ResponseEntity<>("No existe una caja con ese id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cashDrawer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!this.cashDrawerService.delete(id)) {
            return new ResponseEntity<>("No existe una caja con ese id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/open")
    public ResponseEntity<?> open() {
        List<CashDrawer> cashDrawers = this.cashDrawerService.getAll();
        cashDrawers.forEach(cashDrawer -> {
            if (cashDrawer.getPort() == null) {
                EscposCoffee escposCoffee = new EscposCoffee();
                System.out.println(cashDrawer.getPrinter());
                if (cashDrawer.getPrinter() != null) {
                    escposCoffee.OpenCashDrawerFromPrinter(cashDrawer.getPrinter());
                }
            } else {
                System.out.println(cashDrawer.getPort());
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
