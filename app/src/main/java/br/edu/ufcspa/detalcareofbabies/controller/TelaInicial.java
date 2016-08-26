package br.edu.ufcspa.detalcareofbabies.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ufcspa.detalcareofbabies.R;
import br.edu.ufcspa.detalcareofbabies.banco.DataHelper;
import br.edu.ufcspa.detalcareofbabies.banco.TermoUsoDAO;
import br.edu.ufcspa.detalcareofbabies.client.Connection;
import br.edu.ufcspa.detalcareofbabies.client.PrimMesClient;
import br.edu.ufcspa.detalcareofbabies.client.SextoMesClient;
import br.edu.ufcspa.detalcareofbabies.client.TercMesClient;
import br.edu.ufcspa.detalcareofbabies.client.UserClient;
import br.edu.ufcspa.detalcareofbabies.controller.cadastroInicial.TelaCadastroInicial;
import br.edu.ufcspa.detalcareofbabies.controller.dicas.Dicas;
import br.edu.ufcspa.detalcareofbabies.controller.primeiroMes.Tela1PrimeiroMes;

import br.edu.ufcspa.detalcareofbabies.controller.sextoMes.Tela1SextoMes;
import br.edu.ufcspa.detalcareofbabies.controller.terceiroMes.Tela1TerceiroMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.PrimeiroMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.SextoMes;
import br.edu.ufcspa.detalcareofbabies.model.coletas.TerceiroMes;
import br.edu.ufcspa.detalcareofbabies.model.entidade.Crianca;
import br.edu.ufcspa.detalcareofbabies.model.entidade.MySingleton;
import br.edu.ufcspa.detalcareofbabies.model.entidade.StatusApp;
import br.edu.ufcspa.detalcareofbabies.model.entidade.Usuario;

public class TelaInicial extends AppCompatActivity{

    private FloatingActionButton btCadastrar;
    private FloatingActionButton btInfo;
    private FloatingActionButton btChat;
//    static int cntCard =0;

    private StatusApp status;
    private LinearLayout dicaAmamentar;
    private TextView txtViewStatus;

//    public static int getCntCard(){
//        return cntCard;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        iniciaComponentes();
    }

    private void iniciaComponentes() {

        txtViewStatus = (TextView) findViewById(R.id.textViewStatusMensagem);
        iniciaMenu();

        /*

                //Intent trocar = new Intent(getApplicationContext(), TelaCadastroInicial.class);
                //startActivity(trocar);
                Log.d("aapp", "clicou no botao +");
           // openOptionsMenu();
                //-----------------------GAMBIARRA PARA FUNCIONAR O CLIQUE DE BOTAO--------
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Instrumentation inst = new Instrumentation();
                            //for ( int i = 0; i < 10; ++i ) {
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
                            //Thread.sleep(2000);
                            //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                            //Thread.sleep(2000);
                            //}
                        }
                        catch(Exception e){
                        }
                    }
                }).start();
            }
        });
            */

        dicaAmamentar = (LinearLayout) findViewById(R.id.cardAmamentar);
        dicaAmamentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dicas.class));

            }
        });
    }

    private void iniciaMenu() {

        btCadastrar = (FloatingActionButton) findViewById(R.id.botao_cadastro_floating);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.getCadastro() == 0 && status.getPriMes() == 0) {
                    startActivity(new Intent(getApplicationContext(), TelaCadastroInicial.class));
                } else if (status.getCadastro() == 1 && status.getPriMes() == 0) {
                    startActivity(new Intent(getApplicationContext(), Tela1PrimeiroMes.class));
                } else if (status.getCadastro() == 1 && status.getPriMes() == 1 && status.getTercMes()==0) {
                    startActivity(new Intent(getApplicationContext(),Tela1TerceiroMes.class));
                }else if(status.getCadastro() ==1 && status.getTercMes() == 1 && status.getSextMes()==0){
                    startActivity(new Intent(getApplicationContext(),Tela1SextoMes.class));
                }
            }
        });

        btInfo = (FloatingActionButton) findViewById(R.id.botao_info);
        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("erro_telaInfo", "troca de activity não funfa");
                startActivity(new Intent(getApplicationContext(), TelaInfo.class));
            }
        });

        btChat = (FloatingActionButton) findViewById(R.id.botao_chat);
        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Em breve", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sincronizar(boolean message) {
        if (Connection.verificaConexao(this)) {
            Log.d("internet", "Conectado a internet");
            enviaDados(message);
        } else {
            Log.d("internet", "Não esta conectado!");
            if (message) {
                Toast.makeText(this, "Você não possui conexão com a internet no momento", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void enviaDados(boolean message){
        DataHelper dao = new DataHelper(getApplicationContext());
        int termo =TermoUsoDAO.get(dao.getWritableDatabase());
        if(termo==1) // se user aceitou termo de uso do app enviar dados da pesquisa para nuvem
            {
                if (status.getCadastro() == 1 && status.getCadastroSync() == 0) {
                    UserClient client = new UserClient(this, null);
                    Usuario responsavel = dao.getUsuario();
                    Crianca crianca = dao.getCrianca(1);
                    client.postJson(responsavel.toJSON(), crianca.toJSON());
                }
                if (status.getCadastroSync() == 1 && status.getPriMes() == 1 && status.getPriMesSync() == 0) {
                    PrimMesClient client = new PrimMesClient(this);
                    PrimeiroMes pm = dao.getPrimeiroMes(1);
                    client.postJson(pm.toJSON(dao.getIDCrianca()));
                }
                if (status.getCadastroSync() == 1 && status.getTercMes() == 1 && status.getTercMesSync() == 0) {
                    TercMesClient client = new TercMesClient(this);
                    TerceiroMes tm = dao.getTerceiroMes(1);
                    client.postJson(tm.toJSON(dao.getIDCrianca()));
                }
                if(status.getCadastroSync() ==1 && status.getSextMes()==1 && status.getSextMesSync() ==0){
                    SextoMesClient client = new SextoMesClient(this);
                    SextoMes sm = dao.getSextoMes();
                    client.postJson(sm.toJSON(dao.getIDCrianca()));
                }

                if (status.getPriMesSync() == 1 && status.getCadastroSync() == 1) {
                    Log.d("internet", "primeiro mes e cadastro inicial sincronizados!");
                }

                if (message) {
                    Toast.makeText(this, "Sincronização concluída com sucesso!", Toast.LENGTH_LONG).show();
                }
        }
        dao.close();
    }


    private void verificarStatus() {
        if (status.getCadastro() == 0 && status.getPriMes() == 0) {

            txtViewStatus.setText(R.string.inicio_toque_inicial);
            btCadastrar.setTitle("Cadastro Inicial");
        } else if (status.getCadastro() == 1 && status.getPriMes() == 0) {
            try {
                if(compareData()>=30){
                    txtViewStatus.setText(R.string.inicio_toque_primeiro_mes);
                }else{
                    txtViewStatus.setText(R.string.inicio_toque_inicial_data_imcompleta);
                    btCadastrar.setVisibility(View.GONE);
                    btCadastrar.setClickable(false);

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            btCadastrar.setTitle("Cadastro do Primeiro Mês");
        } else if (status.getCadastro() == 1 && status.getPriMes() == 1 && status.getTercMes()==0) {
            try {
                if(compareData()>=90){
                    txtViewStatus.setText(R.string.inicio_toque_terceiro_mes);
                }else{
                    txtViewStatus.setText(R.string.inicio_aguarde_terceiro_mes);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            btCadastrar.setTitle("Cadastro do Terceiro Mês");
        }
        else if(status.getCadastro()==1 && status.getPriMes()==1 && status.getTercMes()==1 && status.getSextMes()==0){
            try{
                if(compareData()>=180){
                    txtViewStatus.setText(R.string.inicio_toque_sexto_mes);
                }else{
                    txtViewStatus.setText(R.string.inicio_aguarde_sexto_mes);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            btCadastrar.setTitle("Sexto mês");
            btCadastrar.setVisibility(View.VISIBLE);
            btCadastrar.setClickable(true);
        }
        else if(status.getTercMes()==1 && status.getSextMes()==1){
            try{
                if(compareData()>=(30*12)){
                    txtViewStatus.setText(R.string.cadastro_primeiroAno);
                    btCadastrar.setVisibility(View.VISIBLE);
                    btCadastrar.setClickable(true);
                    btCadastrar.setTitle("Primeiro Ano");
                }else{
                    txtViewStatus.setText(R.string.inicio_aguarde_primeiroAno);
                    btCadastrar.setVisibility(View.GONE);
                    btCadastrar.setClickable(false);
                                    }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }





    @Override
    protected void onResume() {
        super.onResume();
        getStatus();
        //enviaDados(false);
        sincronizar(false);
        verificarStatus();
        Log.d("status", "status:" + status.toString());


        /*tela_termos de comunicação web via JSON
        Crianca c = new Criança("Elias","08/11/1993","1",2,2,"/mnt/sdcard/Pictures/JPEG_2016-02-03_19_33_32_786621844.jpg");
        Usuario u = new Usuario(1,"123","rafael","08/11/1967",1,2,"viamao","080808","icaromscastro@gmail.com",2,4,false,false,false,true,true,true);
        try{
            UserClient client = new UserClient(this,u);
            client.postJson(u.toJSON(),c.toJSON());
        }catch (Exception e){

        }
        PrimeiroMes pm = new PrimeiroMes(14, 2, 1, 5, 0.5,
        2.0, 3, 2, 1, 1, 3,true, true, true, true,false,
        true, false, false, true, false,false, true, false, true, true,
                false, false, false, false, false,
                false, false, false, false, 35,
                2, 3, 1, true, true,
                true, true, true,"/mnt/sdcard/Pictures/JPEG_2016-02-04_18_08_48_-1808580483.jpg");
        System.out.println("primeiro Mes:\n"+pm.toJSON().toString());
        try{
            PrimMesClient client = new PrimMesClient(this);
            client.postJson(pm.toJSON());
        }catch (Exception e){
            Log.e("app","deu merda:"+e.getMessage());
        }*/

        /*
        --------Teste CRUD do banco sqlite-----------

        DataHelper dataHelper = new DataHelper(this);
        Log.d("banco","tables:"+dataHelper.getTestData());
        Log.d("banco","inserindo...");
        dataHelper.insertAppStatus(new StatusApp());
        Log.d("banco","atualizando...");
        dataHelper.updateStatusCadastro(1);
        dataHelper.updateStatusCadastroSync(1);
        dataHelper.updateStatusPrimMes(1);
        dataHelper.updateStatusPrimMesSync(1);
        Log.d("banco", "selecionando...");
        Log.d("banco", "selecao:"+dataHelper.getStatus().toString());

        PrimeiroMes pm = new PrimeiroMes(14, 2, 1, 5, 0.5,
                2.0, 3, 2, 1, 1, 3,true, true, true, true,false,
                true, false, false, true, false,false, true, false, true, true,
                false, false, false, false, false,
                false, false, false, false, 35,
                2, 3, 1, true, true,
                true, true, true);
        DataHelper dao = new DataHelper(this);
        Log.d("banco","objeto primeiro mes  do app:"+pm.toString());
        //dao.inserirPrimeiroMes(pm);
        pm=dao.getPrimeiroMes(1);
        Log.d("banco","objeto primeiro mes  do bd:"+pm.toString());
        Usuario user = new Usuario(1,"123","rafael","08/11/1967",1,2,"viamao","080808","icaromscastro@gmail.com",2,4,false,false,false,true,true,true);
        Log.d("banco", "objeto responsavel do app:" + user.toString());
        //dao.inserirResp(user);
        //Usuario user;
        user=dao.getUsuario();
        Log.d("banco", "objeto responsavel do bd:" + user.toString());
        Crianca c = new Crianca("Eliot","08/11/1993","1",2,2,"www/foto/feliz.jpg");
        //dao.inserirCria(c);
        Crianca cria=dao.getCrianca(1);
        Log.d("banco", "objeto crianca do app:" + c.toString());
        Log.d("banco", "objeto crianca do bd:" + cria.toString());
        */
    }


    private void getStatus(){
        DataHelper data = new DataHelper(getApplicationContext());
        status=data.getStatus(data.getWritableDatabase());
            if(status.getId()!=0){
               Log.d("banco","Status encontrado no bd");
                Log.d("banco",status.toString());
            }else {
                Log.d("banco", "Status não encontrado no bd");
                //data.insertAppStatus(new StatusApp());
                //status=data.getStatus();
            }
        data.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getStatus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_tela_inicial, menu);

//        //return true;
//        if (status.getCadastro() == 0) {
//            menu.add(0, 2, 0, "Cadastro Inicial");
//        }
//        if (status.getCadastro() == 1 && status.getPriMes() == 0) {
//            menu.add(0, 3, 0, "Cadastro 1° mês");
//        }
//        if (status.getCadastro() == 1 && status.getPriMes() == 1 && status.getTercMes() == 0) {
//            menu.add(0, 4, 0, "Cadastro 3° mês");
//        }

        menu.add(0, 0, 0, "Sobre o projeto");
        menu.add(0, 1, 0, "Sincronizar");
        //menu.add(0, 5, 0, "Teste");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        MySingleton single = MySingleton.getInstance();


        //noinspection SimplifiableIfStatement
        if (id == 0) {
            startActivity(new Intent(getApplicationContext(), TelaSobre.class));
        }
        if (id == 3) {
            if (status.getCadastro() == 1)
                startActivity(new Intent(getApplicationContext(), Tela1PrimeiroMes.class));
            else
                Toast.makeText(this, "Por favor, antes de realizar o cadastro do primeiro mes finalize o seu cadastro inicial",
                        Toast.LENGTH_LONG).show();
        }
        if (id == 2) {
            startActivity(new Intent(getApplicationContext(), TelaCadastroInicial.class));
        }
        if (id == 1) {
            sincronizar(true);
        }
        if (id == 4) {
            Toast.makeText(this, "O cadastro do 3º mes estará disponível em breve", Toast.LENGTH_LONG).show();
        }

        if (id == 5) {
            startActivity(new Intent(getApplicationContext(), Tela1TerceiroMes.class));
        }
        return super.onOptionsItemSelected(item);
    }



    public long compareData() throws ParseException {
        DataHelper data= new DataHelper(getApplicationContext());
        String ini=null;
        ini=data.getCrianca(1).getDataNascCria();
        Log.e("data","data do bb pega do bd:"+ini);
        data.close();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        String end=formattedDate;
        Date dIni=stringToDate(ini);
        Date dEnd=stringToDate(end);
        //int diference=compareToDay(dIni,dEnd);
        Log.e("data","String dt inicial:"+ini);
        Log.e("data","String dt atual:"+end);
        long diff=dEnd.getTime()-dIni.getTime();
        long days = diff / (24 * 60 * 60 * 1000);
        Log.e("data","diferenca em dias:"+days);
        return days;
    }






    public static Date stringToDate(String sDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(sDate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        status=null;
        Runtime.getRuntime().gc();
    }
}
