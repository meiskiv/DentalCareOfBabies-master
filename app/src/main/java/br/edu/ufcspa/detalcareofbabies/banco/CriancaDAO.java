package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.model.entidade.Crianca;

/**
 * Created by icarus on 04/01/16.
 */


public class CriancaDAO extends SQLiteOpenHelper {

    public static final String TABELA = "crianca";
    private String tag="banco";

    public CriancaDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABELA, null, 1);
    }


    public static void createTable(SQLiteDatabase db){
//SQLiteDatabase db =data.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS `crianca` (\n" +
                " `id_crianca` int(11) NOT NULL,\n" +
                " `id_responsavel` int(11) DEFAULT NULL,\n" +
                " `id_primeiro_mes` int(11) DEFAULT NULL,\n" +
                " `id_terceiro_mes` int(11) DEFAULT NULL,\n" +
                " `id_sexto_mes` int(11) DEFAULT NULL,\n" +
                " `id_um_ano` int(11) DEFAULT NULL,\n" +
                " `nome` varchar(100) DEFAULT NULL,\n" +
                " `nascimento` varchar(45) DEFAULT NULL,\n" +
                " `sexo` int(11) DEFAULT NULL,\n" +
                " `peso` double DEFAULT NULL,\n" +
                " `altura` double DEFAULT NULL,\n" +
                " `foto` TEXT DEFAULT NULL\n" +
                ")");
//db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABELA );
        onCreate(db);
    }

    public void inserirCria (Crianca crianca){
        String TABELA="Crianca";
        ContentValues values = new ContentValues();
        values.put("id_crianca", crianca.getId());
        values.put("nome", crianca.getNomeCria());
        values.put("nascimento", crianca.getDataNascCria());
        values.put("sexo", crianca.getSexoCria());
        values.put("peso", crianca.getPesoCriaNasc());
        values.put("altura", crianca.getAltCriaNasc());
        values.put("foto", crianca.getFoto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public void deleteCria (Crianca crianca){

        getWritableDatabase().delete(TABELA, "id=?", new String[]{crianca.getId()+""});
    }

    public Crianca getCrianca(int id) {

        Crianca crianca = new Crianca();
        String sql = "select * from crianca WHERE id =" + id;
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



}
