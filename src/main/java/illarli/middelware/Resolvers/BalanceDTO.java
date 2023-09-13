package illarli.middelware.Resolvers;

import illarli.middelware.Models.Balance;
import illarli.middelware.Models.BalanceType;
import illarli.middelware.Repositories.BalanceTypeRepository;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class BalanceDTO {
    @NotBlank(message = "The id field is required")
    public String id;
    @NotBlank(message = "The port field is required")
    public String port;
    @NotNull(message = "The weight timer field is required")
    public int getWeightTimer;
    @NotNull(message = "The balance type field is required")
    public Long balanceType;

    public BalanceDTO(String id, String port, int getWeightTimer, Long balanceType) {
        this.id = id;
        this.port = port;
        this.getWeightTimer = getWeightTimer;
        this.balanceType = balanceType;
    }

    public BalanceDTO() {
    }

    public Balance getBalanceFromBalanceDTO(BalanceTypeRepository balanceTypeRepository) {
        Optional<BalanceType> balanceType = balanceTypeRepository.findById(this.balanceType);
        if (balanceType.isEmpty()) {
            throw new NullPointerException("Balance type is empty");
        }
        return new Balance(this.id, this.port, this.getWeightTimer, balanceType.get());
    }

    @Override
    public String toString() {
        return "id: " +
                this.id + " port: " +
                this.port + " weight timer: " +
                this.getWeightTimer + " balance type id: " +
                this.balanceType;
    }
}
