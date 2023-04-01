package jeg.productions.exercise_3_formulaires;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        final TextView nom = findViewById(R.id.textViewNomClient);
        final TextView prenom = findViewById(R.id.textViewPrenomClient);
        final TextView sexe = findViewById(R.id.textViewSexeClient);
        final TextView fonction = findViewById(R.id.textViewFonctionClient);
        final TextView travail = findViewById(R.id.textViewTravailClient);
        final TextView com = findViewById(R.id.textViewComClient);
        final Button retour = findViewById(R.id.buttonRet);

        Bundle b = getIntent().getExtras();
        nom.setText(b.getString("nom"));
        prenom.setText(b.getString("prenom"));
        sexe.setText(b.getString("sexe"));
        fonction.setText(b.getString("fonction"));
        travail.setText(b.getString("travail"));
        com.setText(b.getString("com"));

        retour.setOnClickListener (view -> {
            Intent i = new Intent(LandingActivity.this, MainActivity.class);
            startActivity(i);
        });
    }
}