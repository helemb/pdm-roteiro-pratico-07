package com.example.meusetimoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesActivity : AppCompatActivity() {

    private val telefone = "+5538987875656"
    private val email = "helem.email@email.com"
    private val site = "https://github.com/helemb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val nome = intent.getStringExtra("NOME") ?: "Utilizador"
        val tvSaudacao = findViewById<TextView>(R.id.tvSaudacao)
        tvSaudacao.text = "Sobre $nome"

        configurarCliqueTelefone()
        configurarCliqueEmail()
        configurarCliqueSite()
        configurarPartilha(nome)
    }

    private fun configurarCliqueTelefone() {
        findViewById<LinearLayout>(R.id.layoutTelefone).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$telefone")
            }
            startActivity(intent)
        }
    }

    private fun configurarCliqueEmail() {
        findViewById<LinearLayout>(R.id.layoutEmail).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            startActivity(intent)
        }
    }

    private fun configurarCliqueSite() {
        findViewById<LinearLayout>(R.id.layoutSite).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(site)
            }
            startActivity(intent)
        }
    }

    private fun configurarPartilha(nome: String) {
        findViewById<Button>(R.id.btnPartilhar).setOnClickListener {
            val textoPartilha = """
                Contacto: $nome
                Telefone: $telefone
                Email: $email
                Site: $site
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, textoPartilha)
                putExtra(Intent.EXTRA_SUBJECT, "Contacto - $nome")
            }
            startActivity(Intent.createChooser(intent, "Partilhar contacto via"))
        }
    }
}
