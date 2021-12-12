package com.wc.walletcontrol.helper;

import android.annotation.SuppressLint;

import android.util.Log;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){

        // Metodo retorna data representada pelo long
        long data = System.currentTimeMillis();

        // define a formatação da data
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);

        return dataString;
    }

    public static String mesAnoDataEscolhida(String data){

        String[] retornoData = data.split("/");
        String dia = retornoData[0];
        String mes = retornoData[1];
        String ano = retornoData[2];

        String mesAno = mes + ano;

        return mesAno;
    }
}
