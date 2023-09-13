package illarli.middelware.Controllers;

import illarli.middelware.Infrastructure.Balance.JSerialComm;
import illarli.middelware.Models.Balance;
import illarli.middelware.Repositories.BalanceTypeRepository;
import illarli.middelware.Resolvers.BalanceDTO;
import illarli.middelware.Services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;
    @Autowired
    private BalanceTypeRepository balanceTypeRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/com-list")
    public List<String> getComList() {
        JSerialComm jSerialComm = new JSerialComm();
        return this.balanceService.getComList(jSerialComm);
    }

    @GetMapping("/list")
    public List<Balance> index() {
        return balanceService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> save(@Valid @RequestBody BalanceDTO balanceDTO) {
        Balance balance = balanceDTO.getBalanceFromBalanceDTO(balanceTypeRepository);
        return new ResponseEntity<>(balanceService.add(balance), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!balanceService.delete(id)) {
            return new ResponseEntity<>("The id " + id + " does not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(@Payload String text) throws InterruptedException {
        JSerialComm.flag = true;
        JSerialComm jSerialComm = new JSerialComm();
        List<Balance> balances = this.balanceService.getAll();
        if (!balances.isEmpty()) {
            Balance balance = balances.get(0);
            jSerialComm.open(s -> {
                messagingTemplate.convertAndSend("/topic/greetings", s);
                return s;
            }, balance);
        }
        return "";
    }

    @MessageMapping("/stop")
    public void send() {
        JSerialComm.flag = false;
    }

}
