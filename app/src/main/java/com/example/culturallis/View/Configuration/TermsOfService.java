        package com.example.culturallis.View.Configuration;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Entrance.LogIn;
import com.example.culturallis.View.Entrance.LogOn;
import com.example.culturallis.View.Fragments.Loading;

public class TermsOfService extends ModelAppScreens {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_service);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.left_arrow);

        TextView titleTextView = findViewById(R.id.tbTitle);
        titleTextView.setText("Termos de Uso");

        AppCompatButton btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });



        TextView txt = findViewById(R.id.terms);
        txt.setText("Bem-vindo ao Culturallis!\n" +
                "\n" +
                "Estes Termos de Uso e Política de Privacidade (\"Termos\") regem o uso do aplicativo Culturallis, desenvolvido para proporcionar uma experiência enriquecedora de cultura. Ao acessar e utilizar nosso aplicativo, você concorda em cumprir estes Termos e nossa Política de Privacidade. Por favor, leia atentamente.\n" +
                "\n" +
                "1. Uso do Aplicativo\n" +
                "\n" +
                "1.1 Ao utilizar o Culturallis, você concorda em usá-lo apenas para fins legais e de acordo com estes Termos.\n" +
                "\n" +
                "1.2 Você reconhece que todo o conteúdo disponível no aplicativo, incluindo, mas não se limitando a, textos, imagens, vídeos e áudios, é protegido por direitos autorais e propriedade intelectual. Você concorda em não copiar, reproduzir, distribuir ou criar obras derivadas desse conteúdo sem a devida autorização.\n" +
                "\n" +
                "2. Privacidade\n" +
                "\n" +
                "2.1 Respeitamos sua privacidade e estamos comprometidos em proteger suas informações pessoais. Nossa Política de Privacidade detalha como coletamos, usamos e protegemos suas informações. Ao usar o aplicativo, você concorda com as práticas descritas em nossa Política de Privacidade.\n" +
                "\n" +
                "2.2 Coletamos informações pessoais apenas com seu consentimento, e essas informações são utilizadas para melhorar sua experiência no aplicativo e personalizar o conteúdo de acordo com seus interesses.\n" +
                "\n" +
                "2.3 Não compartilhamos suas informações pessoais com terceiros sem seu consentimento, exceto quando exigido por lei ou quando necessário para a prestação de serviços relacionados ao aplicativo.\n" +
                "\n" +
                "3. Conteúdo Gerado pelo Usuário\n" +
                "\n" +
                "3.1 O Culturallis pode permitir que você crie e compartilhe conteúdo, como comentários, avaliações ou contribuições culturais. Você é responsável por garantir que seu conteúdo seja preciso, legal e respeite os direitos de terceiros.\n" +
                "\n" +
                "3.2 Ao fornecer conteúdo gerado pelo usuário, você concede ao Culturallis o direito não exclusivo, irrevogável e global de usar, reproduzir e distribuir esse conteúdo para fins relacionados ao aplicativo.\n" +
                "\n" +
                "4. Rescisão\n" +
                "\n" +
                "4.1 Reservamo-nos o direito de encerrar sua conta e acesso ao aplicativo a qualquer momento, por qualquer motivo, incluindo violações destes Termos.\n" +
                "\n" +
                "4.2 Você pode encerrar seu uso do aplicativo a qualquer momento, excluindo-o de seu dispositivo.\n" +
                "\n" +
                "5. Alterações nos Termos\n" +
                "\n" +
                "5.1 Podemos modificar estes Termos a qualquer momento. As alterações entrarão em vigor após a publicação no aplicativo. Recomendamos verificar regularmente os Termos para estar ciente de qualquer atualização.\n" +
                "\n" +
                "6. Contato\n" +
                "\n" +
                "Se tiver alguma dúvida sobre estes Termos ou nossa Política de Privacidade, entre em contato conosco em [endereço de e-mail de contato].\n" +
                "\n" +
                "Ao continuar a usar o Culturallis, você concorda com estes Termos e nossa Política de Privacidade. Obrigado por escolher nosso aplicativo e esperamos que tenha uma experiência cultural enriquecedora!\n" +
                "\n" +
                "Data de vigência: 2023\n" +
                "\n" +
                "Apoliz\n" +
                "apoliz.cultura@gmail.com\n" +
                "[Website - Landing Page]");
    }

    public void complete(View view){
        startActivity(new Intent(this, MainSettingsScreen.class));
        finish();
    }
}