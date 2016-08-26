package br.edu.ufcspa.detalcareofbabies.model.entidade;

import br.edu.ufcspa.detalcareofbabies.model.coletas.PrimeiroMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.SextoMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.TerceiroMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.UmAno;

/**
 * Created by icarus on 10/12/15.
 */
public class MySingleton {
    private static MySingleton ourInstance = new MySingleton();

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {

    }
    public Crianca crianca= new Crianca();
    public Usuario usuario= new Usuario();
    public PrimeiroMes priMes= new PrimeiroMes();
    public TerceiroMes tercMes= new TerceiroMes();
    public SextoMes sextoMes = new SextoMes();
    public UmAno umAno = new UmAno();
}
