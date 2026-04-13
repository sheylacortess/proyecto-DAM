package clases;

import java.util.ArrayList;

public class Wallet {
    private double saldoEuros;
    private ArrayList<Crypto> cryptos;

    // Constructor
    public Wallet() {
        this.saldoEuros = 0.0;
        this.cryptos = new ArrayList<>();
        cryptos.add(new Crypto("BTC", 0.0, 94000.0));
        cryptos.add(new Crypto("ETH", 0.0, 1600.0));
        cryptos.add(new Crypto("SOL", 0.0, 130.0));
    }

    public double getSaldoEuros() {
        return saldoEuros;
    }

    public void setSaldoEuros(double saldoEuros) {
        this.saldoEuros = saldoEuros;
    }

    public ArrayList<Crypto> getCryptos() {
        return cryptos;
    }

    public void setCryptos(ArrayList<Crypto> cryptos) {
        this.cryptos = cryptos;
    }
}
