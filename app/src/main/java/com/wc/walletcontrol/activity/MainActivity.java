package com.wc.walletcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.SignInButton;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.wc.walletcontrol.R;
import com.wc.walletcontrol.activity.CadastroActivity;
import com.wc.walletcontrol.activity.LoginActivity;

public class MainActivity extends IntroActivity {

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

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }
}