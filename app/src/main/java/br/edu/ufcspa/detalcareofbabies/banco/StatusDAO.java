package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.model.entidade.StatusApp;

/**
 * Created by icarus on 25/01/16.
 */
public class StatusDAO {
    private static final String TB_NAME="app_status";



    public static void createTable(SQLiteDatabase db){
        //SQLiteDatabase db = data.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_status` (\n" +
                "  `id` int(11) NOT NULL, \n" +
                "  `cadastro` INTEGER NOT NULL,\n" +
                "  `cadastro_sync` INTEGER NOT NULL,\n" +
                "  `prim_mes` INTEGER NOT NULL,\n" +
                "  `prim_mes_sync` INTEGER NOT NULL,\n" +
                "  `terc_mes` INTEGER NOT NULL,\n" +
                "  `terc_mes_sync` INTEGER NOT NULL,\n" +
                "  `sexto_mes` INTEGER NOT NULL,\n" +
                "  `sexto_mes_sync` INTEGER NOT NULL,\n" +
                "  `um_ano` INTEGER NOT NULL,\n" +
                "  `um_ano_sync` INTEGER NOT NULL\n" +
                ")");
    }
    public static void insertOLD(SQLiteDatabase db, StatusApp st){
        ContentValues cv=  new ContentValues();
        cv.put("id",1);
        cv.put("cadastro",st.getCadastro());
        cv.put("prim_mes",st.getPriMes());
        cv.put("prim_mes_sync",st.getPriMesSync());
        cv.put("cadastro_sync",st.getCadastroSync());
        cv.put("terc_mes",st.getTercMes());
        cv.put("terc_mes_sync",st.getTercMesSync());
        db.insert(TB_NAME, null, cv);
    }
    public static void insert(SQLiteDatabase db, StatusApp st){
        ContentValues cv=  new ContentValues();
        cv.put("id",1);
        cv.put("cadastro",st.getCadastro());
        cv.put("prim_mes",st.getPriMes());
        cv.put("prim_mes_sync",st.getPriMesSync());
        cv.put("cadastro_sync",st.getCadastroSync());
        cv.put("terc_mes",st.getTercMes());
        cv.put("terc_mes_sync",st.getTercMesSync());
        cv.put("sexto_mes",st.getSextMes());
        cv.put("sexto_mes_sync",st.getSextMesSync());
        cv.put("um_ano",st.getUmAno());
        cv.put("um_ano_sync",st.getUmAnoSync());
        db.insert(TB_NAME, null, cv);
    }


    public static void updateStatusCadastro(SQLiteDatabase db,int cadastro){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("cadastro",cadastro);
        db.update(TB_NAME, cv, "id=" + id, null);
    }
    public static void updateAll(SQLiteDatabase db,StatusApp st){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("cadastro",st.getCadastro());
        cv.put("prim_mes",st.getPriMes());
        cv.put("prim_mes_sync",st.getPriMesSync());
        cv.put("cadastro_sync",st.getCadastroSync());
        cv.put("terc_mes",st.getTercMes());
        cv.put("terc_mes_sync",st.getTercMesSync());
        db.update(TB_NAME, cv, "id=" + id, null);
    }
    public static void updateStatusCadastroSync(SQLiteDatabase db,int cadastroSync){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("cadastro_sync",cadastroSync);
        db.update(TB_NAME, cv, "id=" + id, null);
    }
    public static void updateStatusPrimMes(SQLiteDatabase db,int primMes){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("prim_mes",primMes);
        db.update(TB_NAME,cv,"id="+id,null);
    }

    public static void updateStatusPrimMesSync(SQLiteDatabase db,int primMes){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("prim_mes_sync",primMes);
        db.update(TB_NAME,cv,"id="+id,null);
    }
    public static void updateStatusTercMesSync(SQLiteDatabase db,int tercMesSync){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("terc_mes_sync",tercMesSync);
        db.update(TB_NAME,cv,"id="+id,null);
    }
    public static void updateStatusTercMes(SQLiteDatabase db,int tercMes){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("terc_mes",tercMes);
        db.update(TB_NAME,cv,"id="+id,null);
    }

    public static void updateStatusSextoMes(SQLiteDatabase db,int sextoMes){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("sexto_mes",sextoMes);
        db.update(TB_NAME,cv,"id="+id,null);
        Log.d("banco","atualizou flag sexto mês");
    }

    public static void updateStatusSextoMesSync(SQLiteDatabase db,int sextoMesSync){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("sexto_mes_sync",sextoMesSync);
        db.update(TB_NAME,cv,"id="+id,null);
    }

    public static void updateStatusUmAno(SQLiteDatabase db,int umAno){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("um_ano",umAno);
        db.update(TB_NAME,cv,"id="+id,null);
    }
    public static void updateStatusUmAnoSync(SQLiteDatabase db,int umAnoSync){
        ContentValues cv=  new ContentValues();
        int id=1;
        cv.put("um_ano_sync",umAnoSync);
        db.update(TB_NAME,cv,"id="+id,null);
    }

    public static int getStatusTermoUso(SQLiteDatabase db){
        String sql = "SELECT termo_uso FROM "+TB_NAME+" WHERE id=1;";
        Cursor  c = db.rawQuery(sql, null);
        int status= 0;
        if(c.moveToFirst()){
            status=c.getInt(0);
        }
        return status;
    }

    public static StatusApp getStatus(SQLiteDatabase db){
        String sql = "SELECT * FROM "+TB_NAME+" WHERE id=1;";
        Cursor  c = db.rawQuery(sql, null);
        StatusApp status = new StatusApp();
        if(c.moveToFirst()){
            status.setId(c.getInt(0));
            status.setCadastro(c.getInt(1));
            status.setCadastroSync(c.getInt(2));
            status.setPriMes(c.getInt(3));
            status.setPriMesSync(c.getInt(4));
            status.setTercMes(c.getInt(5));
            status.setTercMesSync(c.getInt(6));
            status.setSextMes(c.getInt(7));
            status.setSextMesSync(c.getInt(8));
            status.setUmAno(c.getInt(9));
            status.setUmAnoSync(10);
            //status.setTermoUso(c.getInt(7));
        }
        return status;
    }

    public static StatusApp getStatusV1(SQLiteDatabase db){
        String sql = "SELECT * FROM "+TB_NAME+" WHERE id=1;";
        Cursor  c = db.rawQuery(sql, null);
        StatusApp status = new StatusApp();
        if(c.moveToFirst()){
            status.setId(c.getInt(0));
            status.setCadastro(c.getInt(1));
            status.setCadastroSync(c.getInt(2));
            status.setPriMes(c.getInt(3));
            status.setPriMesSync(c.getInt(4));
            //status.setTermoUso(c.getInt(7));
        }
        return status;
    }









}
