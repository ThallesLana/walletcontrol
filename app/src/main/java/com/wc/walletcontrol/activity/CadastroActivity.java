package com.wc.walletcontrol.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wc.walletcontrol.R;
import com.wc.walletcontrol.config.ConfiguracaoFirebase;
import com.wc.walletcontrol.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoCpf, campoTel, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome   = findViewById(R.id.editName);
        campoEmail  = findViewById(R.id.editEmail);
        campoCpf    = findViewById(R.id.editCpf);
        campoTel    = findViewById(R.id.editPhone);
        campoSenha  = findViewById(R.id.editPassword);
        botaoCadastrar = findViewById(R.id.buttonCad);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome    = campoNome.getText().toString();
                String textoEmail   = campoEmail.getText().toString();
                String textoCpf     = campoCpf.getText().toString();
                String textoTel     = campoTel.getText().toString();
                String textoSenha   = campoSenha.getText().toString();

                // Valida se os campos foram preenchidos

                if ( !textoNome.isEmpty() ){
                    if ( !textoEmail.isEmpty() ){
                        if ( !textoCpf.isEmpty() ){
                            if ( !textoTel.isEmpty() ){
                                if ( !textoSenha.isEmpty() ){
                                    usuario = new Usuario();
                                    usuario.setNome( textoNome );
                                    usuario.setEmail( textoEmail );
                                    usuario.setCpf( textoCpf );
                                    usuario.setTel( textoTel );
                                    usuario.setSenha( textoSenha );
                                    cadastrarUsuario();
                                }else{
                                    Toast.makeText(CadastroActivity.this, "Preencha o campo Senha!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(CadastroActivity.this, "Preencha o campo Telefone!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(CadastroActivity.this, "Preencha o campo CPF!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroActivity.this, "Preencha o campo Email!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this, "Preencha o campo Nome!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){
                    Toast.makeText(CadastroActivity.this, "Suceso ao cadastrar o usúario!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar o usúario!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}