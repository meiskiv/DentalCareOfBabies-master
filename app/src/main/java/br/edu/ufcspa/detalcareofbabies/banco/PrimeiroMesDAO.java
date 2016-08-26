package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.model.coletas.PrimeiroMes;

/**
 * Created by icarus on 25/01/16.
 */
public class PrimeiroMesDAO extends SQLiteOpenHelper{

        public static final String TABELA = "primeiro_mes";
        private String tag="banco";

        public PrimeiroMesDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, TABELA, null, 1);
        }

        public static void createTable(SQLiteDatabase db){
        //SQLiteDatabase db = data.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS `primeiro_mes` (\n" +
                "  `id_primeiro_mes` int(11) NOT NULL,\n" +
                "  `estado_civil` int(11) DEFAULT NULL,\n" +
                "  `renda_familiar` int(11) DEFAULT NULL,\n" +
                "  `consultas_pre_natais` int(11) DEFAULT NULL,\n" +
                "  `peso_do_bebe` double DEFAULT NULL,\n" +
                "  `altura_do_bebe` double DEFAULT NULL,\n" +
                "  `gestacoes` int(11) DEFAULT NULL,\n" +
                "  `partos_normais` int(11) DEFAULT NULL,\n" +
                "  `cesarias` int(11) DEFAULT NULL,\n" +
                "  `abortos` int(11) DEFAULT NULL,\n" +
                "  `tipo_parto` int(11) DEFAULT NULL,\n" +
                "  `c_sofrimento_fetal` tinyint(1) DEFAULT NULL,\n" +
                "  `c_desproporcao` tinyint(1) DEFAULT NULL,\n" +
                "  `c_hemorragia` tinyint(1) DEFAULT NULL,\n" +
                "  `c_parada` tinyint(1) DEFAULT NULL,\n" +
                "  `c_eclampsia` tinyint(1) DEFAULT NULL,\n" +
                "  `c_pos_maturidade` tinyint(1) DEFAULT NULL,\n" +
                "  `c_pre_eclampsia` tinyint(1) DEFAULT NULL,\n" +
                "  `c_diabete` tinyint(1) DEFAULT NULL,\n" +
                "  `c_trompas` tinyint(1) DEFAULT NULL,\n" +
                "  `c_repeticao` tinyint(1) DEFAULT NULL,\n" +
                "  `c_morte_fetal` tinyint(1) DEFAULT NULL,\n" +
                "  `c_eletiva` tinyint(1) DEFAULT NULL,\n" +
                "  `c_outra` tinyint(1) DEFAULT NULL,\n" +
                "  `c_desconhecida` tinyint(1) DEFAULT NULL,\n" +
                "  `i_pos_maturidade` tinyint(1) DEFAULT NULL,\n" +
                "  `i_pre_eclampsia` tinyint(1) DEFAULT NULL,\n" +
                "  `i_bolsa_rota` tinyint(1) DEFAULT NULL,\n" +
                "  `i_iso_imunizacao` tinyint(1) DEFAULT NULL,\n" +
                "  `i_morte_fetal` tinyint(1) DEFAULT NULL,\n" +
                "  `i_eletiva` tinyint(1) DEFAULT NULL,\n" +
                "  `i_outra` tinyint(1) DEFAULT NULL,\n" +
                "  `i_desconhecida` tinyint(1) DEFAULT NULL,\n" +
                "  `idade_em_dias` int(11) DEFAULT NULL,\n" +
                "  `condicao_saude` int(11) DEFAULT NULL,\n" +
                "  `alimentacao` int(11) DEFAULT NULL,\n" +
                "  `dias_no_hospital` int(11) DEFAULT NULL,\n" +
                "  `icterisia_importante` tinyint(1) DEFAULT NULL,\n" +
                "  `trauma` tinyint(1) DEFAULT NULL,\n" +
                "  `diarreia` tinyint(1) DEFAULT NULL,\n" +
                "  `infeccao_respiratoria` tinyint(1) DEFAULT NULL,\n" +
                "  `outra_infeccao` tinyint(1) DEFAULT NULL,\n" +
                "  `foto` text,\n" +
                "  `id_crianca` INTEGER\n" +
                ")");
    }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABELA);
                onCreate(db);
        }




        public void inserirPrimeiroMes(PrimeiroMes primeiromes){

                ContentValues values = new ContentValues();

                values.put("id_crianca",primeiromes.getIdCrianca());
                values.put("estado_civil",primeiromes.getEstCivil());
                values.put("renda_familiar",primeiromes.getRendaFamiliar());
                values.put("consultas_pre_natais",primeiromes.getConsultasPrenatais());
                values.put("peso_do_bebe",primeiromes.getPesoBebe());
                values.put("altura_do_bebe",primeiromes.getAlturaBebe());
                values.put("gestacoes",primeiromes.getnGestacoes());
                values.put("partos_normais",primeiromes.getnPartNorm());
                values.put("cesarias",primeiromes.getnCesaria());
                values.put("abortos",primeiromes.getnAbort());
                values.put("tipo_parto",primeiromes.getTipoPart());
                //covert booleans to integer
                values.put("c_sofrimento_fetal",toInt(primeiromes.iscSofrFet()));
                values.put("c_desproporcao",toInt(primeiromes.iscDesprop()));
                values.put("c_hemorragia",toInt(primeiromes.iscHemorragia()));
                values.put("c_parada",toInt(primeiromes.iscParada()));
                values.put("c_eclampsia",toInt(primeiromes.iscEclampsia()));
                values.put("c_pos_maturidade",toInt(primeiromes.iscPosMatur()));
                values.put("c_pre_eclampsia",toInt(primeiromes.iscPreEclamp()));
                values.put("c_diabete",toInt(primeiromes.iscDiabet()));
                values.put("c_trompas",toInt(primeiromes.iscTrompas()));
                values.put("c_repeticao",toInt(primeiromes.iscRepeticao()));
                values.put("c_morte_fetal",toInt(primeiromes.iscMortFetal()));
                values.put("c_eletiva",toInt(primeiromes.iscEletiva()));
                values.put("c_outra",toInt(primeiromes.iscOutra()));
                values.put("c_desconhecida",toInt(primeiromes.iscDesconh()));
                values.put("i_pos_maturidade",primeiromes.isiPosMat());
                values.put("i_pre_eclampsia",primeiromes.isiPreEclamp());
                values.put("i_bolsa_rota",primeiromes.isiBolsRot());
                values.put("i_iso_imunizacao",primeiromes.isiIsolmunizacao());
                values.put("i_morte_fetal",primeiromes.isiMorteFetal());
                values.put("i_eletiva",primeiromes.isiEletiva());
                values.put("i_outra",primeiromes.isiOutra());
                values.put("i_desconhecida",primeiromes.isiDesconh());
                values.put("idade_em_dias",primeiromes.getIdadeCriaEmDias());
                values.put("condicao_saude",primeiromes.getCondicaoSaude());
                values.put("alimentacao",primeiromes.getAlimentacao());
                values.put("dias_no_hospital",primeiromes.getDiasHosp());
                values.put("icterisia_importante",primeiromes.isIcterisiaImp());
                values.put("trauma",primeiromes.isTrauma());
                values.put("diarreia",primeiromes.isDiarreia());
                values.put("infeccao_respiratoria",primeiromes.isInfecResp());
                values.put("outra_infeccao",primeiromes.isOutraInf());
                values.put("foto","tela_termos");

                getWritableDatabase().insert(TABELA, null, values);

        }

        public void deletePrimeiroMes(PrimeiroMes primeiromes) {

                getWritableDatabase().delete(TABELA, "id=?", new String[]{primeiromes.getIdCrianca() + ""});

        }


        public PrimeiroMes getPrimeiroMes(int id){

                String sql = "select * from primeiro_mes WHERE id_primeiro_mes =" + id;
                Cursor c =getWritableDatabase().rawQuery(sql, null);
                PrimeiroMes primeiromes = new PrimeiroMes();
                if (c.moveToFirst()) {

                        try {
                                //primeiromes = new PrimeiroMes();
                                primeiromes.setIdCrianca(c.getInt(0));
                                primeiromes.setEstCivil(c.getInt(1));
                                primeiromes.setRendaFamiliar(c.getInt(2));
                                primeiromes.setConsultasPrenatais(c.getInt(3));
                                primeiromes.setPesoBebe(c.getDouble(4));
                                primeiromes.setAlturaBebe(c.getInt(5));
                                primeiromes.setnGestacoes(c.getInt(6));
                                primeiromes.setnPartNorm(c.getInt(7));
                                primeiromes.setnCesaria(c.getInt(8));
                                primeiromes.setnAbort(c.getInt(9));
                                primeiromes.setTipoPart(c.getInt(10));
                                primeiromes.setcSofrFet(toBoolean(c.getInt(11)));
                                primeiromes.setcDesprop(toBoolean(c.getInt(12)));
                                primeiromes.setcHemorragia(toBoolean(c.getInt(13)));
                                primeiromes.setcParada(toBoolean(c.getInt(14)));
                                primeiromes.setcEclampsia(toBoolean(c.getInt(15)));
                                primeiromes.setcPosMatur(toBoolean(c.getInt(16)));
                                primeiromes.setcPreEclamp(toBoolean(c.getInt(17)));
                                primeiromes.setcDiabet(toBoolean(c.getInt(18)));
                                primeiromes.setcTrompas(toBoolean(c.getInt(19)));
                                primeiromes.setcRepeticao(toBoolean(c.getInt(20)));
                                primeiromes.setcMortFetal(toBoolean(c.getInt(21)));
                                primeiromes.setcEletiva(toBoolean(c.getInt(22)));
                                primeiromes.setcOutra(toBoolean(c.getInt(23)));
                                primeiromes.setcDesconh(toBoolean(c.getInt(24)));
                                primeiromes.setiPosMat(toBoolean(c.getInt(25)));
                                primeiromes.setiPreEclamp(toBoolean(c.getInt(26)));
                                primeiromes.setiBolsRot(toBoolean(c.getInt(27)));
                                primeiromes.setiIsolmunizacao(toBoolean(c.getInt(28)));
                                primeiromes.setiMorteFetal(toBoolean(c.getInt(29)));
                                primeiromes.setiEletiva(toBoolean(c.getInt(30)));
                                primeiromes.setiOutra(toBoolean(c.getInt(31)));
                                primeiromes.setiDesconh(toBoolean(c.getInt(32)));
                                primeiromes.setIdadeCriaEmDias(c.getInt(33));
                                primeiromes.setCondicaoSaude(c.getInt(34));
                                primeiromes.setAlimentacao(c.getInt(35));
                                primeiromes.setDiasHosp(c.getInt(36));
                                primeiromes.setIcterisiaImp(toBoolean(c.getInt(37)));
                                primeiromes.setTrauma(toBoolean(c.getInt(38)));
                                primeiromes.setDiarreia(toBoolean(c.getInt(39)));
                                primeiromes.setInfecResp(toBoolean(c.getInt(40)));
                                primeiromes.setOutraInf(toBoolean(c.getInt(41)));
                                primeiromes.setFoto(c.getString(42));



                        } catch (Exception v) {
                                Log.e(tag, "erro ao pegar dados do banco:" + v.getMessage());
                        }

                        c.close();

                } else {
                        Log.d(tag, "não retornoun nada");
                }

                return primeiromes;

        }

        public boolean toBoolean(int v){
                return (v==1);
        }
        public int toInt(boolean v){
                int myInt = (v) ? 1 : 0;
                return myInt;
        }

}






