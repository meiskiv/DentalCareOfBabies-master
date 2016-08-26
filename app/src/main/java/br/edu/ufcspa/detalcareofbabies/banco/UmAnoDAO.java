package br.edu.ufcspa.detalcareofbabies.banco;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by icarus on 25/07/16.
 */
public class UmAnoDAO {
    public static final String TABELA = "um_ano";
    private String tag="banco";
    public static void createTable(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS `um_ano` (\n" +
                "  `id_um_ano` int(11) NOT NULL ,\n" +
                "  `peso_do_bebe` double DEFAULT NULL,\n" +
                "  `altura_do_bebe` double DEFAULT NULL,\n" +
                "  `amamenta` tinyint(1) DEFAULT NULL,\n" +
                "  `leite_materno` int(11) DEFAULT NULL,\n" +
                "  `escovacao` int(11) DEFAULT NULL,\n" +
                "  `chupeta` tinyint(1) DEFAULT NULL,\n" +
                "  `foto` TEXT DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id_um_ano`)\n" +
                ")";
        db.execSQL(sql);
    }
}
