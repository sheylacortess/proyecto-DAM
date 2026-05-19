package clases;

import java.util.ArrayList;
import java.util.HashMap;

public class Wallet {
    private double saldoEuros;
    private HashMap<String, Crypto> cryptos;

    // Constructor
    public Wallet() {
        this.saldoEuros = 0.0;
        this.cryptos = new HashMap<>();
        cryptos.put("BTC", new Crypto("BTC", 0.0, 94000.0));
        cryptos.put("ETH", new Crypto("ETH", 0.0, 1600.0));
        cryptos.put("SOL", new Crypto("SOL", 0.0, 130.0));
    }

    public double getSaldoEuros() {
        return saldoEuros;
    }

    public void setSaldoEuros(double saldoEuros) {
        this.saldoEuros = saldoEuros;
    }

    public HashMap<String, Crypto> getCryptos() {
        return cryptos;
    }

    public void setCryptos(HashMap<String, Crypto> cryptos) {
        this.cryptos = cryptos;
    }
}
