package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.model.coletas.PrimeiroMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.SextoMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.TerceiroMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.UmAno;
import br.edu.ufcspa.detalcareofbabies.model.entidade.Crianca;
import br.edu.ufcspa.detalcareofbabies.model.entidade.StatusApp;
import br.edu.ufcspa.detalcareofbabies.model.entidade.Usuario;

/***
 * Created by icarus on 27/11/15.
 * classe usada para realizar comunicação com o SGBD SQLITE
 **/
public class DataHelper extends SQLiteOpenHelper {
    private String tag = "banco";
    protected static final String DATABASE_NAME = "dentalcare";
    protected static final int VERSION =4;
    protected SQLiteDatabase data;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        data = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("banco", "onCreate sqlite");
        UserDAO.createTable(db);
        CriancaDAO.createTable(db);
        StatusDAO.createTable(db);
        PrimeiroMesDAO.createTable(db);
        TerceiroMesDAO.createTable(db);
        TermoUsoDAO.createTable(db);
        TermoUsoDAO.insert(db, -1);
        QuestDAO.createTable(db);
        QuestDAO.insert(db, -1);
        StatusDAO.insert(db, new StatusApp());
        //atualização
        SextoMesDAO.createTable(db);
        UmAnoDAO.createTable(db);

    }

    public String getTestData() {
        //SQLiteDatabase db =getWritableDatabase();
        String sql = "SELECT name FROM sqlite_master WHERE type='table'";
        //String sql = "SELECT Count(*) FROM tb_activity_student";

        Cursor mCur = data.rawQuery(sql, null);
        String tabelas = "";
        if (mCur.moveToFirst()) {
            do {
                tabelas += mCur.getString(0) + "\n";
            } while (mCur.moveToNext());
        }
        return tabelas;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("banco", "onUpgrade base versao:" + db.getVersion());
        //Log.d("banco", "onUpgrade base versao old:" + getWritableDatabase().getVersion());
        StatusApp s = getStatusV1(db);
        if (s.getCadastro() == 1) {
//            Crianca c = getCrianca(1);
//            //DROP TABLES ALTERED IN VERSIONS
//            db.execSQL("DROP TABLE IF EXISTS crianca");
//            db.execSQL("DROP TABLE iF EXISTS app_status");
//            //CREATE TABLES ALTERED IN VERSION
//            CriancaDAO.createTable(db);
//            StatusDAO.createTable(db);
//
//            //INSERT INTO ALTERED TABLES DATA FROM THE LOWER VERSION
//            StatusDAO.insert(db,s);
//            inserirCria(c);
            migrarDados(db, s);
        } else {
            onCreate(db);
        }
        //StatusDAO.updateAll(db,s);
    }


    public void migrarDados(SQLiteDatabase db, StatusApp s) {

        //Crianca c = getCrianca(1);
        Log.d("BANCO", "MIGRANDO DADOS DA VERSÃO ANTIGA DO SQLITE!");

        //DROP DATA
        //db.execSQL("DROP TABLE IF EXISTS crianca");
        db.execSQL("DROP TABLE IF EXISTS app_status");
        db.execSQL("DROP TABLE IF EXISTS termo_uso");
        //db.execSQL("DROP TABLE iF EXISTS primeiro_mes");
        //db.execSQL("DROP TABLE iF EXISTS terceiro_mes");


        //CREATE IT AGAIN
        //CriancaDAO.createTable(db);
        StatusDAO.createTable(db);
        TermoUsoDAO.createTable(db);
        TerceiroMesDAO.createTable(db);
        SextoMesDAO.createTable(db);
        UmAnoDAO.createTable(db);
        QuestDAO.createTable(db);
        QuestDAO.insert(db, -1);
        //INSERT DATA IN NEW TABLES
        StatusDAO.insert(db, s);
        //inserirCria(c);
        TermoUsoDAO.insert(db, -1); // -1 is dafault value for this table

    }
    ///--------------CRUD STATUS APP ----------------------///

    public void insertAppStatus(StatusApp st) {
        StatusDAO.insert(data, st);
    }

    public StatusApp getStatus(SQLiteDatabase db) {
        return StatusDAO.getStatus(db);
    }

    public StatusApp getStatusV1(SQLiteDatabase db) {
        return StatusDAO.getStatusV1(db);
    }

    public void updateStatusCadastro(int cadastro) {
        StatusDAO.updateStatusCadastro(data, cadastro);
    }

    public void updateStatusCadastroSync(int cadastroSync) {
        StatusDAO.updateStatusCadastroSync(data, cadastroSync);
    }

    public void updateStatusPrimMes(int prim) {
        StatusDAO.updateStatusPrimMes(data, prim);
    }

    public void updateStatusPrimMesSync(int primSync) {
        StatusDAO.updateStatusPrimMesSync(data, primSync);
    }

    public void updateStatusTercMes(int terc) {
        StatusDAO.updateStatusTercMes(data, terc);
    }

    public void updateStatusTercMesSync(int terc) {
        StatusDAO.updateStatusTercMesSync(data, terc);
    }


    //-------------------- CRUD UM ANO ----------------------------------

    public void inserirUmAno(UmAno u) {

        String TABELA = "um_ano";
        ContentValues values = new ContentValues();
        values.put("id_um_ano",1);
        values.put("peso_do_bebe",u.getPesoBebe());
        values.put("altura_do_bebe",u.getAlturaBebe());
        values.put("amamenta",toInt(u.isAmamenta()));
        values.put("leite_materno",u.getAlimentacaoComplementar());
        values.put("escovacao",u.getEscovacaoDia());
        values.put("chupeta",toInt(u.isChupeta()));
        values.put("foto",u.getFotoPerfil());

        try {
            getWritableDatabase().insert(TABELA, null, values);
            Log.d(tag, "Um Ano inserido com sucesso");
        } catch (Exception e) {
            Log.e(tag, "erro ao inserir Um Ano:" + e.getMessage());
        }
    }

    public UmAno getUmAno() {

        String sql = "select * from um_ano WHERE id_um_ano =" + 1;
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        UmAno umAno = new UmAno();
        if (c.moveToFirst()) {
            try {
                //Log.d("BANCO","column names:"+ c.getColumnNames().toString());
                for (String nam : c.getColumnNames())
                    Log.d("BANCO", "column names:" + nam);
                umAno.setPesoBebe(c.getDouble(1));
                umAno.setAlturaBebe(c.getDouble(2));
                umAno.setAmamenta(toBoolean(c.getInt(3)));
                umAno.setAlimentacaoComplementar(c.getInt(4));
                umAno.setEscovacaoDia(c.getInt(5));
                umAno.setChupeta(toBoolean(c.getInt(6)));
                umAno.setFotoPerfil(c.getString(7));
            } catch (Exception v) {
                Log.e(tag, "erro ao pegar dados do banco da tabela um ano:" + v.getMessage());
            }

            c.close();

        } else {
            Log.d(tag, "não retornoun nada do select um ano");
        }

        return umAno;
    }







    //-------------------- CRUD SEXTO MES ----------------------------------

    public void inserirSextoMes(SextoMes s) {

        String TABELA = "sexto_mes";
        ContentValues values = new ContentValues();
        values.put("id_sexto_mes", 1);
        values.put("peso_do_bebe", s.getPesoBebe());
        values.put("altura_do_bebe", s.getAltBebe());
        values.put("frutas", s.isFrutas());
        values.put("ovos", s.isOvos());
        values.put("arroz", s.isArroz());
        values.put("feijao", s.isFeijao());
        values.put("mingau", s.isMingau());
        values.put("legumes", s.isLegumes());
        values.put("massas", s.isMassas());
        values.put("batata", s.isBatata());
        values.put("carne", s.isCarnes());
        values.put("alimento_doce", s.isAlimentoDoce());
        if (s.getQualAlimentoDoce() != null) {
            values.put("qual_doce", s.getQualAlimentoDoce());
        }
        values.put("refrigerante", s.isRefri());
        values.put("dentes", s.isDente());
        values.put("foto", s.getFoto());

        try {
            getWritableDatabase().insert(TABELA, null, values);
            Log.d(tag, "Sexto mes inserido com sucesso");
        } catch (Exception e) {
            Log.e(tag, "erro ao inserir sexto mes:" + e.getMessage());
        }
    }

    public SextoMes getSextoMes() {

        String sql = "select * from sexto_mes WHERE id_sexto_mes =" + 1;
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        SextoMes sMes = new SextoMes();
        if (c.moveToFirst()) {
            try {
                //Log.d("BANCO","column names:"+ c.getColumnNames().toString());
                for (String nam : c.getColumnNames())
                    Log.d("BANCO", "column names:" + nam);
                sMes.setPesoBebe(c.getDouble(1));
                sMes.setAltBebe(c.getDouble(2));
                sMes.setFrutas(toBoolean(c.getInt(3)));
                sMes.setOvos(toBoolean(c.getInt(4)));
                sMes.setArroz(toBoolean(c.getInt(5)));
                sMes.setFeijao(toBoolean(c.getInt(6)));
                sMes.setMingau(toBoolean(c.getInt(7)));
                sMes.setLegumes(toBoolean(c.getInt(8)));
                sMes.setMassas(toBoolean(c.getInt(9)));
                sMes.setBatata(toBoolean(c.getInt(10)));
                sMes.setCarnes(toBoolean(c.getInt(11)));
                sMes.setAlimentoDoce(toBoolean(c.getInt(12)));
                sMes.setQualAlimentoDoce(c.getString(13));
                sMes.setRefri(toBoolean(c.getInt(14)));
                sMes.setDente(toBoolean(c.getInt(15)));
                sMes.setFoto(c.getString(16));
            } catch (Exception v) {
                Log.e(tag, "erro ao pegar dados do banco:" + v.getMessage());
            }

            c.close();

        } else {
            Log.d(tag, "não retornoun nada");
        }

        return sMes;
    }





    //-------------------- CRUD TERCEIRO MES ----------------------------------


    public TerceiroMes getTerceiroMes(int id) {

        String sql = "select * from terceiro_mes WHERE id_terceiro_mes =" + 1;
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        TerceiroMes tMes = new TerceiroMes();
        if (c.moveToFirst()) {
            try {
                //Log.d("BANCO","column names:"+ c.getColumnNames().toString());
                for (String nam : c.getColumnNames())
                    Log.d("BANCO", "column names:" + nam);
                tMes.setIdTercMes(c.getInt(0));
                tMes.setPesoBebe(c.getDouble(1));
                tMes.setAltBebe(c.getDouble(2));
                tMes.setUsaChupeta(toBoolean(c.getInt(3)));
                tMes.setFreqChup(c.getInt(4));
                tMes.setAlimentacao(c.getInt(5));
                tMes.setAchocolatado(toBoolean(c.getInt(6)));
                tMes.setCha(toBoolean(c.getInt(7)));
                tMes.setCafe(toBoolean(c.getInt(8)));
                tMes.setSuco(toBoolean(c.getInt(9)));
                tMes.setRefri(toBoolean(c.getInt(10)));
                tMes.setCaldoAlim(toBoolean(c.getInt(11)));
                tMes.setLeiteEmPo(toBoolean(c.getInt(12)));
                tMes.setLeiteMaterno(toBoolean(c.getInt(13)));
                tMes.setHigienizacaoBoca(toBoolean(c.getInt(14)));
                tMes.setFoto(c.getString(15));

            } catch (Exception v) {
                Log.e(tag, "erro ao pegar dados do banco:" + v.getMessage());
            }

            c.close();

        } else {
            Log.d(tag, "não retornoun nada");
        }

        return tMes;
    }

    public void inserirTerceiroMes(TerceiroMes t) {
        String TABELA = "terceiro_mes";
        ContentValues values = new ContentValues();
        values.put("id_terceiro_mes", 1);
        values.put("peso_do_bebe", t.getPesoBebe());
        values.put("altura_do_bebe", t.getAltBebe());
        values.put("chupeta", t.isUsaChupeta());
        values.put("frequencia_chupeta", t.getFreqChup());
        values.put("alimentacao", t.getAlimentacao());
        values.put("achocolatado", t.isAchocolatado());
        values.put("cha", t.isCha());
        values.put("cafe", t.isCafe());
        values.put("suco", t.isSuco());
        values.put("refrigerante", t.isRefri());
        values.put("caldo_de_alimentos", t.isCaldoAlim());
        values.put("leite_em_po", t.isLeiteEmPo());
        values.put("leite_materno", t.isLeiteMaterno());
        values.put("higienizacao_da_boca", t.isHigienizacaoBoca());
        values.put("foto", t.getFoto());

        try {
            getWritableDatabase().insert(TABELA, null, values);
            Log.d(tag, "Responsavel inserido com sucesso");
        } catch (Exception e) {
            Log.e(tag, "erro ao inserir terceiro mes:" + e.getMessage());
        }

    }


    //-------------------- CRUD PRIMEIRO MES ----------------------------------


    public PrimeiroMes getPrimeiroMes(int id) {

        String sql = "select * from primeiro_mes WHERE id_primeiro_mes =" + id;
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        PrimeiroMes primeiromes = new PrimeiroMes();
        if (c.moveToFirst()) {

            try {
                //primeiromes = new PrimeiroMes();
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
                primeiromes.setIdCrianca(c.getInt(43));


            } catch (Exception v) {
                Log.e(tag, "erro ao pegar dados do banco:" + v.getMessage());
            }

            c.close();

        } else {
            Log.d(tag, "não retornoun nada");
        }

        return primeiromes;

    }

    public void inserirPrimeiroMes(PrimeiroMes primeiromes) {
        String TABELA = "primeiro_mes";
        ContentValues values = new ContentValues();

        values.put("id_primeiro_mes", 1);
        values.put("estado_civil", primeiromes.getEstCivil());
        values.put("renda_familiar", primeiromes.getRendaFamiliar());
        values.put("consultas_pre_natais", primeiromes.getConsultasPrenatais());
        values.put("peso_do_bebe", primeiromes.getPesoBebe());
        values.put("altura_do_bebe", primeiromes.getAlturaBebe());
        values.put("gestacoes", primeiromes.getnGestacoes());
        values.put("partos_normais", primeiromes.getnPartNorm());
        values.put("cesarias", primeiromes.getnCesaria());
        values.put("abortos", primeiromes.getnAbort());
        values.put("tipo_parto", primeiromes.getTipoPart());
        //covert booleans to integer
        values.put("c_sofrimento_fetal", toInt(primeiromes.iscSofrFet()));
        values.put("c_desproporcao", toInt(primeiromes.iscDesprop()));
        values.put("c_hemorragia", toInt(primeiromes.iscHemorragia()));
        values.put("c_parada", toInt(primeiromes.iscParada()));
        values.put("c_eclampsia", toInt(primeiromes.iscEclampsia()));
        values.put("c_pos_maturidade", toInt(primeiromes.iscPosMatur()));
        values.put("c_pre_eclampsia", toInt(primeiromes.iscPreEclamp()));
        values.put("c_diabete", toInt(primeiromes.iscDiabet()));
        values.put("c_trompas", toInt(primeiromes.iscTrompas()));
        values.put("c_repeticao", toInt(primeiromes.iscRepeticao()));
        values.put("c_morte_fetal", toInt(primeiromes.iscMortFetal()));
        values.put("c_eletiva", toInt(primeiromes.iscEletiva()));
        values.put("c_outra", toInt(primeiromes.iscOutra()));
        values.put("c_desconhecida", toInt(primeiromes.iscDesconh()));
        values.put("i_pos_maturidade", primeiromes.isiPosMat());
        values.put("i_pre_eclampsia", primeiromes.isiPreEclamp());
        values.put("i_bolsa_rota", primeiromes.isiBolsRot());
        values.put("i_iso_imunizacao", primeiromes.isiIsolmunizacao());
        values.put("i_morte_fetal", primeiromes.isiMorteFetal());
        values.put("i_eletiva", primeiromes.isiEletiva());
        values.put("i_outra", primeiromes.isiOutra());
        values.put("i_desconhecida", primeiromes.isiDesconh());
        values.put("idade_em_dias", primeiromes.getIdadeCriaEmDias());
        values.put("condicao_saude", primeiromes.getCondicaoSaude());
        values.put("alimentacao", primeiromes.getAlimentacao());
        values.put("dias_no_hospital", primeiromes.getDiasHosp());
        values.put("icterisia_importante", primeiromes.isIcterisiaImp());
        values.put("trauma", primeiromes.isTrauma());
        values.put("diarreia", primeiromes.isDiarreia());
        values.put("infeccao_respiratoria", primeiromes.isInfecResp());
        values.put("outra_infeccao", primeiromes.isOutraInf());
        values.put("foto", primeiromes.getFoto());
        values.put("id_crianca", primeiromes.getIdCrianca());
        try {
            getWritableDatabase().insert(TABELA, null, values);
            Log.d(tag, "conseguiu inserir na tabela primeiro mes com sucesso");
        } catch (Exception e) {
            Log.e(tag, "erro ao inserir primeiro mes:" + e.getMessage());
        }

    }

    //------------------------ CRUD USUARIO----------------------
    public Usuario getUsuario() {

        Usuario usuario = new Usuario();
        String sql = "select * from responsavel WHERE id_responsavel =" + 1;
        Cursor c = getWritableDatabase().rawQuery(sql, null);

        if (c.moveToFirst()) {
            usuario.setId(c.getInt(0));
            usuario.setnSus(c.getString(1));
            usuario.setNome(c.getString(2));
            usuario.setDataNasc(c.getString(3));
            usuario.setSexo(c.getInt(4));
            usuario.setCor(c.getInt(5));
            usuario.setCidade(c.getString(6));
            usuario.setTelefone(c.getString(7));
            usuario.setEmail(c.getString(10));
            usuario.setFormacao(c.getInt(11));
            usuario.setTipoTrab(c.getInt(12));
            usuario.setFumante(toBoolean(c.getInt(13)));
            usuario.setAlcoolatra(toBoolean(c.getInt(14)));
            usuario.setDrogas(toBoolean(c.getInt(15)));
            usuario.setHipert(toBoolean(c.getInt(16)));
            usuario.setDiabet(toBoolean(c.getInt(17)));
            usuario.setAvc(toBoolean(c.getInt(18)));
            c.close();
        }

        return usuario;
    }

    public void inserirResp(Usuario usuario) {
        String TABELA = "responsavel";
        ContentValues values = new ContentValues();
        values.put("id_responsavel", 1);
        values.put("cartao_sus", usuario.getnSus());
        values.put("nome", usuario.getNome());
        values.put("nascimento", usuario.getDataNasc());
        values.put("sexo", usuario.getSexo());
        values.put("raca", usuario.getCor());
        values.put("cidade", usuario.getCidade());
        values.put("telefone1", usuario.getTelefone());
        values.put("telefone2", "00");
        values.put("telefone3", "00");
        values.put("email", usuario.getEmail());
        values.put("escolaridade", usuario.getFormacao());
        values.put("trabalho", usuario.getTipoTrab());
        values.put("cigarro", usuario.isFumante());
        values.put("alcool", usuario.isAlcoolatra());
        values.put("drogas", usuario.isDrogas());
        values.put("hipertensao", usuario.isHipert());
        values.put("diabetes", usuario.isDiabet());
        values.put("avc", usuario.isAvc());
        try {
            getWritableDatabase().insert(TABELA, null, values);
            Log.d(tag, "Responsavel inserido com sucesso");
        } catch (Exception e) {
            Log.e(tag, "erro ao inserir responsavel:" + e.getMessage());
        }

    }


    // ----------------- CRUD CRIANCA ------------------

    public void inserirCria(Crianca crianca) {
        String TABELA = "Crianca";
        ContentValues values = new ContentValues();
        if (crianca.getId() > 1) {
            values.put("id_crianca", crianca.getId());
        } else {
            values.put("id_crianca", 1);
        }

        values.put("nome", crianca.getNomeCria());
        values.put("nascimento", crianca.getDataNascCria());
        values.put("sexo", crianca.getSexoCria());
        values.put("peso", crianca.getPesoCriaNasc());
        values.put("altura", crianca.getAltCriaNasc());
        values.put("foto", crianca.getFoto());
        try {
            getWritableDatabase().insert(TABELA, null, values);
            Log.d(tag, "CRIANCA inserida com sucesso");
        } catch (Exception e) {
            Log.e(tag, "erro ao inserir CRIANCA:" + e.getMessage());
        }
    }

    public String getDataCrianca() {
        String sql = "select nascimento from crianca";
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        String data = "null";
        if (c.moveToFirst()) {
            data = c.getString(0);
            Log.d(tag, "data nascimento crianca:" + data);
        }
        return data;
    }

    public void updateIdCrianca(int novaID) {
        String TABELA = "Crianca";
        ContentValues values = new ContentValues();
        values.put("id_crianca", novaID);
        getWritableDatabase().update(TABELA, values, "id_crianca=1", null);
    }

    public Crianca getCrianca(int id) {

        Crianca crianca = new Crianca();
        String sql = "select * from crianca";
        Cursor c = getWritableDatabase().rawQuery(sql, null);

        if (c.moveToFirst()) {

            try {
                //crianca = new Crianca();
                crianca.setId(c.getInt(0));
                crianca.setNomeCria(c.getString(6));
                crianca.setDataNascCria(c.getString(7));
                crianca.setSexoCria(c.getInt(8));
                crianca.setPesoCriaNasc(c.getDouble(9));
                crianca.setAltCriaNasc(c.getDouble(10));
                crianca.setFoto(c.getString(11));


            } catch (Exception v) {

                Log.e(tag, "erro ao pegar dados do banco:" + v.getMessage());
            }


            c.close();

        } else {
            Log.d(tag, "não retornoun nada");
        }


        return crianca;
    }

    public int getIDCrianca() {

        int id = 0;
        String sql = "select id_crianca from crianca";
        Cursor c = getWritableDatabase().rawQuery(sql, null);

        if (c.moveToFirst()) {

            try {
                id = c.getInt(0);

            } catch (Exception v) {

                Log.e(tag, "erro ao pegar dados do banco:" + v.getMessage());
            }


            c.close();

        } else {
            Log.d(tag, "não retornoun nada");
        }

        return id;
    }


    public boolean toBoolean(int v) {
        return (v == 1);
    }

    public int toInt(boolean v) {
        int myInt = (v) ? 1 : 0;
        return myInt;
    }


}
