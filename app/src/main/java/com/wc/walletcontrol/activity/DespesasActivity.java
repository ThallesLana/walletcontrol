package com.wc.walletcontrol.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wc.walletcontrol.R;
import com.wc.walletcontrol.config.ConfiguracaoFirebase;
import com.wc.walletcontrol.helper.Base64Custom;
import com.wc.walletcontrol.helper.DateCustom;
import com.wc.walletcontrol.model.Movimentacao;
import com.wc.walletcontrol.model.Usuario;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;
    private Double despesaGerada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        // Utilizando findViewById
        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

        //configuração do campo data atual
        campoData.setText(DateCustom.dataAtual());
        recuperarDespesaTotal();
    }

    public void salvarDespesa(View view){

        if (validarcamposDespesa()){

            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");

            despesaGerada = valorRecuperado;
            Double despesaAtualizada = despesaTotal + despesaGerada;
            atualizarDespesa(despesaAtualizada);
            movimentacao.salvar(data);

        }

    }

    // Validação de preenchimento dos campos de despesa
    public Boolean validarcamposDespesa(){

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if ( !textoValor.isEmpty() ){
            if ( !textoData.isEmpty() ){
                if ( !textoCategoria.isEmpty() ){
                    if ( !textoDescricao.isEmpty() ){
                        return true;
                    }else {
                        Toast.makeText(DespesasActivity.this, "Descrição não foi preenchida!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else {
                    Toast.makeText(DespesasActivity.this, "Categoria não foi preenchida!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(DespesasActivity.this, "Data não foi preenchida!", Toast.LENGTH_SHORT).show();
                return false;
            }

        }else {
            Toast.makeText(DespesasActivity.this, "Valor não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void recuperarDespesaTotal(){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        DatabaseReference usuarioRef = firebaseRef.child("Usuario").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue( Usuario.class );
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void atualizarDespesa(Double despesa){

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        DatabaseReference usuarioRef = firebaseRef.child("Usuario").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(despesa);
    }

}