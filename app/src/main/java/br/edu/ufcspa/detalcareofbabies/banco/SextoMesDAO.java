package br.edu.ufcspa.detalcareofbabies.banco;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by icarus on 25/07/16.
 */
public class SextoMesDAO {

    public static final String TABELA = "sexto_mes";
    private String tag="banco";
    public static void createTable(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS `sexto_mes` (\n" +
                "  `id_sexto_mes` int(11) NOT NULL ,\n" +
                "  `peso_do_bebe` double DEFAULT NULL,\n" +
                "  `altura_do_bebe` double DEFAULT NULL,\n" +
                "  `frutas` tinyint(1) DEFAULT NULL,\n" +
                "  `ovos` tinyint(1) DEFAULT NULL,\n" +
                "  `arroz` tinyint(1) DEFAULT NULL,\n" +
                "  `feijao` tinyint(1) DEFAULT NULL,\n" +
                "  `mingau` tinyint(1) DEFAULT NULL,\n" +
                "  `legumes` tinyint(1) DEFAULT NULL,\n" +
                "  `massas` tinyint(1) DEFAULT NULL,\n" +
                "  `batata` tinyint(1) DEFAULT NULL,\n" +
                "  `carne` tinyint(1) DEFAULT NULL,\n" +
                "  `alimento_doce` tinyint(1) DEFAULT NULL,\n" +
                "  `qual_doce` tinyint(1) DEFAULT NULL,\n" +
                "  `refrigerante` tinyint(1) DEFAULT NULL,\n" +
                "  `dentes` tinyint(1) DEFAULT NULL,\n" +
                "  `foto` TEXT DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id_sexto_mes`)\n" +
                ")";
        db.execSQL(sql);
    }



}
