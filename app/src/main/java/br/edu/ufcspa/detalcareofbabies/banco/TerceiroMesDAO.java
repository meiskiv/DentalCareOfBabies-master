package br.edu.ufcspa.detalcareofbabies.banco;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by icarus on 02/03/16.
 */
public class TerceiroMesDAO {
    public static final String TABELA = "terceiro_mes";
    private String tag="banco";
    public static void createTable(SQLiteDatabase db){
        String sql ="CREATE TABLE IF NOT EXISTS `terceiro_mes` (\n" +
                "  `id_terceiro_mes` int(11) NOT NULL,\n" +
                "  `peso_do_bebe` double DEFAULT NULL,\n" +
                "  `altura_do_bebe` double DEFAULT NULL,\n" +
                "  `chupeta` tinyint(1) DEFAULT NULL,\n" +
                "  `frequencia_chupeta` int(11) DEFAULT NULL,\n" +
                "  `alimentacao` int(11) DEFAULT NULL,\n" +
                "  `achocolatado` tinyint(1) DEFAULT NULL,\n" +
                "  `cha` tinyint(1) DEFAULT NULL,\n" +
                "  `cafe` tinyint(1) DEFAULT NULL,\n" +
                "  `suco` tinyint(1) DEFAULT NULL,\n" +
                "  `refrigerante` tinyint(1) DEFAULT NULL,\n" +
                "  `caldo_de_alimentos` tinyint(1) DEFAULT NULL,\n" +
                "  `leite_em_po` tinyint(1) DEFAULT NULL,\n" +
                "  `leite_materno` tinyint(1) DEFAULT NULL,\n" +
                "  `higienizacao_da_boca` tinyint(1) DEFAULT NULL,\n" +
                "  `foto` TEXT DEFAULT NULL\n" +
                ")";
        db.execSQL(sql);
    }
}
