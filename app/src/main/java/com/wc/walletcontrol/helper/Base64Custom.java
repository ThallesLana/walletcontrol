package com.wc.walletcontrol.helper;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Base64Custom {

    public static String codificarBase64(String texto){

        byte[] data = null;

        try{
            data = texto.getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){

            e.printStackTrace();

        }

       return Base64.encodeToString(data, Base64.NO_WRAP);
    }

    public static String decodificarBase64(String textoCodificado){
        byte[] dataDec = Base64.decode(textoCodificado, Base64.DEFAULT);

        String decodeString = "";

        try{
            decodeString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }finally {
            return decodeString;
        }
    }

}
