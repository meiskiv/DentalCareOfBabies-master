package br.edu.ufcspa.detalcareofbabies.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.edu.ufcspa.detalcareofbabies.model.entidade.Usuario;

/**
 * Created by icarus on 04/01/16.
 */
public class UserDAO extends SQLiteOpenHelper {

        public static final String TABELA = "responsavel";
        private String tag="banco";

        public UserDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                super(context, TABELA, null, 1);
        }

        public static void createTable(SQLiteDatabase db) {
                //SQLiteDatabase db = data.getWritableDatabase();
                db.execSQL("CREATE TABLE IF NOT EXISTS `responsavel` (\n" +
                        "  `id_responsavel` int(11) NOT NULL,\n" +
                        "  `cartao_sus` varchar(45) DEFAULT NULL,\n" +
                        "  `nome` varchar(100) DEFAULT NULL,\n" +
                        "  `nascimento` varchar(45) DEFAULT NULL,\n" +
                        "  `sexo` int(11) DEFAULT NULL,\n" +
                        "  `raca` int(11) DEFAULT NULL,\n" +
                        "  `cidade` varchar(80) DEFAULT NULL,\n" +
                        "  `telefone1` varchar(45) DEFAULT NULL,\n" +
                        "  `telefone2` varchar(45) DEFAULT NULL,\n" +
                        "  `telefone3` varchar(45) DEFAULT NULL,\n" +
                        "  `email` varchar(80) DEFAULT NULL,\n" +
                        "  `escolaridade` int(11) DEFAULT NULL,\n" +
                        "  `trabalho` int(11) DEFAULT NULL,\n" +
                        "  `cigarro` tinyint(1) DEFAULT NULL,\n" +
                        "  `alcool` tinyint(1) DEFAULT NULL,\n" +
                        "  `drogas` tinyint(1) DEFAULT NULL,\n" +
                        "  `hipertensao` tinyint(1) DEFAULT NULL,\n" +
                        "  `diabetes` tinyint(1) DEFAULT NULL,\n" +
                        "  `avc` tinyint(1) DEFAULT NULL\n" +
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


        public void inserirResp(Usuario usuario) {

                ContentValues values = new ContentValues();

                values.put("cartao_sus", usuario.getnSus());
                values.put("nome", usuario.getNome());
                values.put("nascimento", usuario.getDataNasc());
                values.put("sexo", usuario.getSexo());
                values.put("raca", usuario.getCor());
                values.put("cidade", usuario.getCidade());
                values.put("telefone1", usuario.getTelefone());
                values.put("telefone2", "");
                values.put("telefone3", "");
                values.put("email", usuario.getEmail());
                values.put("escolaridade", usuario.getFormacao());
                values.put("trabalho", usuario.getTipoTrab());
                values.put("cigarro", usuario.isFumante());
                values.put("alcool", usuario.isAlcoolatra());
                values.put("drogas", usuario.isDrogas());
                values.put("hipertensao", usuario.isHipert());
                values.put("diabetes", usuario.isDiabet());
                values.put("avc", usuario.isAvc());

                getWritableDatabase().insert(TABELA, null, values);
        }


        public void deleteUsuario(Usuario usuario) {

                getWritableDatabase().delete(TABELA, "id=?", new String[]{usuario.getId() + ""});

        }

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
                        usuario.setEmail(c.getString(8));
                        usuario.setFormacao(c.getInt(9));
                        usuario.setTipoTrab(c.getInt(10));
                        usuario.setFumante(toBoolean(c.getInt(11)));
                        usuario.setAlcoolatra(toBoolean(c.getInt(12)));
                        usuario.setDrogas(toBoolean(c.getInt(13)));
                        usuario.setHipert(toBoolean(c.getInt(14)));
                        usuario.setDiabet(toBoolean(c.getInt(15)));
                        usuario.setAvc(toBoolean(c.getInt(16)));


                        c.close();




                }

                       return usuario;
        }

        public boolean toBoolean(int v){
                return (v==1);
        }
        public int toInt(boolean v){
                int myInt = (v) ? 1 : 0;
                return myInt;
        }



}