package com.wc.walletcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.wc.walletcontrol.R;
import com.wc.walletcontrol.activity.CadastroActivity;
import com.wc.walletcontrol.activity.LoginActivity;
import com.wc.walletcontrol.config.ConfiguracaoFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // Oculta o botão Back
        setButtonBackVisible(false);
        // Oculta o botão next
        setButtonNextVisible(false);
        // Oculta o indicador da barra
        setPagerIndicatorVisible(false);

        // Adicionando slides iniciais ao projeto
        addSlide( new FragmentSlide.Builder()
            .background(android.R.color.black)
            .fragment(R.layout.intro_1)
            .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.black)
                .fragment(R.layout.intro_2)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.black)
                .fragment(R.layout.intro_3)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.black)
                .fragment(R.layout.intro_4)
                //atributos para avanço de slider
                // .canGoBackward(true)
                // .canGoForward(false)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoBackward(true)
                .canGoForward(false)
                .build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        // volta o usuario para a tela inicial ao inves de logar automaticamente
        autenticacao.signOut();
        if (autenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }
}
