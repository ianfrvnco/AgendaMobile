package com.example.agendateste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtTelefone;
    private Button btnSalvar;
    private com.example.agendateste.PessoaDAO dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        btnSalvar = findViewById(R.id.btnSalvar);
        dao = new com.example.agendateste.PessoaDAO(this);
        //linha de baixo utilizada para atualizar update
        Intent it = getIntent();
        if (it.hasExtra("pessoa")) {
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            edtNome.setText(pessoa.getNome());
            edtCpf.setText(pessoa.getCpf());
            edtTelefone.setText(pessoa.getTelefone());

        }
    }

    public void salvar(View view) {
        if (pessoa == null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(edtNome.getText().toString());
            pessoa.setCpf(edtCpf.getText().toString());
            pessoa.setTelefone(edtTelefone.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida no ID de nº:" + id, Toast.LENGTH_LONG).show();
        } else {
            pessoa.setNome(edtNome.getText().toString());
            pessoa.setCpf(edtCpf.getText().toString());
            pessoa.setTelefone(edtTelefone.getText().toString());
            dao.atualizar(pessoa);
            Toast.makeText(this, pessoa.getNome() + ", atualizado com sucesso !!!", Toast.LENGTH_LONG).show();
        }

    }

}