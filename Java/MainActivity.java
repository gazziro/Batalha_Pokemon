package com.example.prova;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView bulba;        //imgs do pokemon
    private ImageView charman;      //imgs do pokemon
    private TextView Nome_pokemon;  //edit nome
    private EditText Nome_Charmander; //edit nome do pokemon
    private Button Atacar;      //acao de atacar
    private Button Capturar;    //acao de capturar
    private Button Fugir;       //acao de fugir
    private Button Trocar;      //acao de trocar
    private int hp_inimigo = 50;
    private int hp_charman = 50;
    private TextView hp_text_charman;   //hp do charmander
    private TextView hp_text_inimigo;   //hp do inimigo
    private CheckBox squirtle;          //checkboxes para trocar de pokemon
    private CheckBox pikachu;           //checkboxes para trocar de pokemon
    private CheckBox bulbasaur;         //checkboxes para trocar de pokemon

    private int[] imagensBulbaIds = { //possiveis imagens em que o adversário vai se situar
            R.drawable.bulbasaur,
            R.drawable.pokeball,
            R.drawable.squirtle,
            R.drawable.pikachu,

    };
    private int[] imagensCharmanIds = { //possiveis imagens em que o charmander vai se situar
            R.drawable.charmander,
            R.drawable.smoke,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) { //declaração dos objetos
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Nome_pokemon = (TextView) findViewById(R.id.Nome_pokemon);
        Nome_Charmander = (EditText) findViewById(R.id.Nome_Charmander);

        squirtle = (CheckBox) findViewById(R.id.CheckBoxSquirtle);
        bulbasaur = (CheckBox) findViewById(R.id.CheckBoxBulbasaur);
        pikachu = (CheckBox) findViewById(R.id.CheckBoxPikachu);

        hp_text_inimigo = (TextView) findViewById(R.id.hp_text_inimigo);
        hp_text_charman = (TextView) findViewById(R.id.hp_text_charman);

        bulba = findViewById(R.id.bulba);
        charman = findViewById(R.id.charman);
        Atacar = findViewById(R.id.Atacar);
        Capturar = findViewById(R.id.Capturar);
        Fugir = findViewById(R.id.Fugir);
        Trocar = findViewById(R.id.Trocar);

        Trocar.setOnClickListener(new EscutadorBotaotrocar());
        Atacar.setOnClickListener(new EscutadorBotaoAtaque());
        Capturar.setOnClickListener(new EscutadorBotaoCapturar());
        Fugir.setOnClickListener(new EscutadorBotaoFugir());

    }

    class EscutadorBotaotrocar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if((squirtle.isChecked() && pikachu.isChecked())|| (bulbasaur.isChecked() && pikachu.isChecked())|| (squirtle.isChecked() && bulbasaur.isChecked())){
                Toast.makeText(getApplicationContext(), "Erro!, selecione apenas uma checkbox!", Toast.LENGTH_SHORT).show();
            }
            else if(pikachu.isChecked()) {
                int j = 3;
                bulba.setImageResource(imagensBulbaIds[j]);
                Nome_pokemon.setText("Pikachu");
                Toast.makeText(getApplicationContext(), "Trocou para Pikachu!", Toast.LENGTH_SHORT).show();

            }
            else if(squirtle.isChecked()){
                int j = 2;
                Nome_pokemon.setText("Squirtle");
                bulba.setImageResource(imagensBulbaIds[j]);
                Toast.makeText(getApplicationContext(), "Trocou para Squirtle!", Toast.LENGTH_SHORT).show();
            }

            else if(bulbasaur.isChecked()) {
                int j = 0;
                bulba.setImageResource(imagensBulbaIds[j]);
                Nome_pokemon.setText("Bulbasaur");
                Toast.makeText(getApplicationContext(), "Trocou para Bulbasaur!", Toast.LENGTH_SHORT).show();

            }
        }

    }

    class EscutadorBotaoAtaque implements View.OnClickListener{
        @Override
        public void onClick(View view){
            int ataqueRandom = new Random().nextInt(3)+1;
            if(hp_inimigo <= 0){                //Confirmação de Derrota (ao inves de deixar -10,-20, ele fica em 0)
                hp_text_inimigo.setText("HP: "+hp_inimigo+"/50"); //Atualiza
                Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();
                ImageView imgView = (ImageView)findViewById(R.id.bulba);
                imgView.setVisibility(view.INVISIBLE);

                Handler handler = new Handler();    //delay
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        finish();
                    }
                }, 3300);
            }
            else if(hp_charman < 0){
                hp_charman = 0;                 //Confirmação de Derrota (ao inves de deixar -10,-20, ele fica em 0)

                hp_text_charman.setText("HP: "+hp_charman+"/50"); //Atualiza
                Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();
                ImageView imgView = (ImageView)findViewById(R.id.charman);
                imgView.setVisibility(view.INVISIBLE);

                Handler handler = new Handler();    //delay
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 3300);
            }
            if (hp_inimigo == 50) {       //primeiro atacar do seu pokemon impossivel morrer nessa situação
                switch (ataqueRandom){
                    case 1:
                        hp_inimigo = hp_inimigo - 20;                         //Adversário perde hp
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50"); //atualiza

                        hp_charman = hp_charman - 10;                         //Charmander perde hp
                        hp_text_charman.setText("HP: " + hp_charman + "/50"); //atualiza

                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" atacou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" atacou!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        hp_inimigo = hp_inimigo - 40;                           //Adversário perde hp por ataque critico
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");   //atualiza

                        hp_charman = hp_charman - 20;                           //Charmander perde hp por ataque critico
                        hp_text_charman.setText("HP: " + hp_charman + "/50");   //atualiza

                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" atacou com um crítico!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" Acertou um critico!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50"); //não acontece nada, pois errou
                        hp_text_charman.setText("HP: " + hp_charman + "/50"); //não acontece nada, pois errou

                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " Falhou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" Falhou!", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            else if (hp_charman == 10){         //situação em que o charmander perde
                Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" usou ataque rápido!", Toast.LENGTH_SHORT).show();
                hp_charman = 0;
                hp_text_charman.setText("HP: "+hp_charman+"/50");

                ImageView imgView2 = (ImageView)findViewById(R.id.charman);
                imgView2.setVisibility(view.INVISIBLE);

                Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();           //esse código serve para dar um delay qnd acaba a batalha
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        finish();
                    }
                }, 3300);           //3300 milésimos
            }

            else if(hp_charman == 20){      //Caso seja critico, charmander é derrotado
                switch (ataqueRandom){
                    case 1:
                        //ataque normal
                        hp_inimigo = 0;
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");

                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" atacou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" atacou!", Toast.LENGTH_SHORT).show();

                        hp_charman = hp_charman - 10;
                        hp_text_charman.setText("HP: " + hp_charman + "/50");

                        break;
                    case 2:
                        //Ataque critico - Charmander Derrotado
                        hp_charman = 0;
                        hp_text_charman.setText("HP: " + hp_charman + "/50");
                        ImageView imgView2 = (ImageView)findViewById(R.id.charman);
                        imgView2.setVisibility(view.INVISIBLE);
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" acertou com um critíco!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();           //esse código serve para dar um delay qnd acaba a batalha
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 3300);
                    break;
                    case 3:
                        // Errou
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " Falhou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" Falhou!", Toast.LENGTH_SHORT).show();

                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");
                        hp_text_charman.setText("HP: " + hp_charman + "/50");

                        break;

                }
            }
            else if (hp_inimigo == 30){
                Toast.makeText(getApplicationContext(), "É um bom momento para capturá-lo!", Toast.LENGTH_SHORT).show();
                switch (ataqueRandom) {                                                 ////////// só é possivel capturar a partir desse hp
                    case 1:
                        //ataque normal Bulba sai com 10 de hp
                        hp_inimigo = hp_inimigo - 20; //ataque normal
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " atacou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" atacou!", Toast.LENGTH_SHORT).show();
                        hp_charman = hp_charman - 10;
                        hp_text_charman.setText("HP: " + hp_charman + "/50");
                        break;

                    case 2:
                        //Ataque critico Bulba morre
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " atacou com um crítico!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();
                        hp_inimigo = 0;
                        hp_text_inimigo.setText("HP: "+hp_inimigo+"/50");

                        ImageView imgView = (ImageView)findViewById(R.id.bulba);
                        imgView.setVisibility(view.INVISIBLE);

                        Handler handler = new Handler();    //delay
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 3300);

                        break;

                    case 3:
                        //Falhou
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " Falhou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" Falhou!", Toast.LENGTH_SHORT).show();

                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");
                        hp_text_charman.setText("HP: " + hp_charman + "/50");
                        break;

                }
            }
            else if(hp_inimigo == 10){ //se sofrer qualquer golpe o adversario morre
                switch (ataqueRandom) {
                    case 1:
                        //ataque normal
                        hp_inimigo = 0;
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " atacou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText() + " foi derrotado!", Toast.LENGTH_SHORT).show();

                        ImageView imgView = (ImageView)findViewById(R.id.bulba);
                        imgView.setVisibility(view.INVISIBLE);

                        Handler handler = new Handler();    //delay
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 3300);

                        break;
                    case 2:
                        //ataque critico
                        hp_inimigo = 0;
                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " atacou com um crítico!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();

                        imgView = (ImageView)findViewById(R.id.bulba);
                        imgView.setVisibility(view.INVISIBLE);

                        handler = new Handler();    //delay
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 3300);

                        break;
                    case 3:
                        //Errou
                        Toast.makeText(getApplicationContext(), Nome_Charmander.getText() + " Falhou!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), Nome_pokemon.getText()+" Falhou!", Toast.LENGTH_SHORT).show();

                        hp_text_inimigo.setText("HP: " + hp_inimigo + "/50");
                        hp_text_charman.setText("HP: " + hp_charman + "/50");
                        break;

                }
            }

        }

    }
    class EscutadorBotaoCapturar implements View.OnClickListener{ ////////////////////////// Capturar
        @Override
        public void onClick(View view){
            if(hp_charman == 10 && hp_inimigo > 40){ //
                Toast.makeText(getApplicationContext(), Nome_Charmander.getText()+" foi derrotado!", Toast.LENGTH_SHORT).show();

                //o seu pokémon nao bate qnd faz a ação de capturar

                ImageView imgView2 = (ImageView)findViewById(R.id.charman);
                imgView2.setVisibility(view.INVISIBLE);         //efeito de morte
                hp_charman = 0;
                hp_text_charman.setText("HP: "+hp_charman+"/50");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {         //delay

                        finish();
                    }
                }, 3300);

            }
            else if (hp_inimigo == 50){ // o minimo para capturar é de 40 de hp
                Toast.makeText(getApplicationContext(), "Você não consegue capturar porque ele está com muito HP!", Toast.LENGTH_SHORT).show();
                if (hp_charman > 0) {
                    hp_charman = hp_charman - 10;
                    hp_text_charman.setText("HP: " + hp_charman + "/50");
                }
            }

            else if(hp_inimigo < 40){ // libera a captura
                Toast.makeText(getApplicationContext(), "Você e " +Nome_Charmander.getText()+" conseguiram capturar!", Toast.LENGTH_SHORT).show();
                int i = 1;
                bulba.setImageResource(imagensBulbaIds[i]);

                Handler handler = new Handler();    //delay
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        finish();
                    }
                }, 3300);
            }

        }

    }
    class EscutadorBotaoFugir implements View.OnClickListener{ //////////////////////////// Fugir não tem segredo
        @Override
        public void onClick(View view){
            Toast.makeText(getApplicationContext(), "Você e "+Nome_Charmander.getText()+" fugiram em segurança!", Toast.LENGTH_SHORT).show();
            int i = 1; //altera a imagem por uma fumacinha
            charman.setImageResource(imagensCharmanIds[i]); //array com a lista de imagens em que o charmander possa estar
            Handler handler = new Handler();        // delay
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finish();
                }
            }, 3000);
        }

    }
}