package illarli.middelware.Controllers;

import illarli.middelware.Models.BalanceType;
import illarli.middelware.Services.BalanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/balance-type")
public class BalanceTypeController {
    @Autowired
    private BalanceTypeService balanceTypeService;

    @GetMapping("/list")
    public List<BalanceType> all() {
        return this.balanceTypeService.getAll();
    }
}
