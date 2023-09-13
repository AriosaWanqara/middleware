package illarli.middelware.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CheckController {
    @GetMapping("/check")
    public boolean checkServerStatus() {
        return true;
    }

    @GetMapping("/shut-down")
    public void turnoff() {
        System.exit(1);
    }
}
