package illarli.middelware.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class BalanceType {
    @Id
    private Long id;
    private String port;
    private int BaudRate;
    private int DataBits;
    private int StopBits;
    private int Parity;
    private byte[] Command;

    public BalanceType(Long id, String port, int baudRate, int dataBits, int stopBits, int parity, byte[] command) {
        this.id = id;
        this.port = port;
        BaudRate = baudRate;
        DataBits = dataBits;
        StopBits = stopBits;
        Parity = parity;
        Command = command;
    }

    protected BalanceType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getBaudRate() {
        return BaudRate;
    }

    public void setBaudRate(int baudRate) {
        BaudRate = baudRate;
    }

    public int getDataBits() {
        return DataBits;
    }

    public void setDataBits(int dataBits) {
        DataBits = dataBits;
    }

    public int getStopBits() {
        return StopBits;
    }

    public void setStopBits(int stopBits) {
        StopBits = stopBits;
    }

    public int getParity() {
        return Parity;
    }

    public void setParity(int parity) {
        Parity = parity;
    }

    public byte[] getCommand() {
        return Command;
    }

    public void setCommand(byte[] command) {
        Command = command;
    }

    @Override
    public String toString() {
        return "BalanceType{" +
                "id=" + id +
                ", port='" + port + '\'' +
                ", BaudRate=" + BaudRate +
                ", DataBits=" + DataBits +
                ", StopBits=" + StopBits +
                ", Parity=" + Parity +
                ", Command=" + Arrays.toString(Command) +
                '}';
    }
}
