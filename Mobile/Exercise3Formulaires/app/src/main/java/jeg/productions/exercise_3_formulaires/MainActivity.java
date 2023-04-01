package jeg.productions.exercise_3_formulaires;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        final Button buttonEnv = (Button) findViewById(R.id.buttonEnv);
        final Button buttonEff = (Button) findViewById(R.id.buttonEff);

        final EditText textNom = (EditText) findViewById(R.id.editTextNom);
        final EditText textPrenom = (EditText) findViewById(R.id.editTextPrenom);
        final EditText textCom = (EditText) findViewById(R.id.editTextCom);

        final CheckBox checkBoxJournalier = (CheckBox) findViewById(R.id.checkBoxJournalier);
        final CheckBox checkBoxTPa = (CheckBox) findViewById(R.id.checkBoxTPa);
        final CheckBox checkBoxTPl = (CheckBox) findViewById(R.id.checkBoxTPl);
        final CheckBox checkBoxOcc = (CheckBox) findViewById(R.id.checkBoxOcc);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        buttonEnv.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LandingActivity.class);
            Bundle b = new Bundle();
            String erreureFonction = getResources().getString(R.string.erreureFonction);

            // get selected radio button from radioGroup
            int selectedId = radioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            RadioButton radioButton = (RadioButton) findViewById(selectedId);

            //populate Bundle + add to Intent
            b.putString("nom", String.valueOf(textNom.getText()));
            b.putString("prenom", String.valueOf(textPrenom.getText()));
            b.putString("sexe", String.valueOf(radioButton.getText()));
            b.putString("fonction", (String) spinner.getSelectedItem());
            if(checkBoxJournalier.isChecked()){
                b.putString("travail", String.valueOf(checkBoxJournalier.getText()));
            }else if(checkBoxOcc.isChecked()){
                b.putString("travail", String.valueOf(checkBoxOcc.getText()));
            }else if(checkBoxTPa.isChecked()){
                b.putString("travail", String.valueOf(checkBoxTPa.getText()));
            }else if(checkBoxTPl.isChecked()){
                b.putString("travail", String.valueOf(checkBoxTPl.getText()));
            }else{
                b.putString("travail", erreureFonction);
            }
            b.putString("com", String.valueOf(textCom.getText()));

            i.putExtras(b);

            startActivity(i);

            //Toast
//            Context context = getApplicationContext();
//            Toast.makeText(context,
//                    "hello",
//                    Toast.LENGTH_LONG).show();
        });

        buttonEff.setOnClickListener(view -> {
            textNom.setText("");
            textPrenom.setText("");
            textCom.setText("");

            checkBoxJournalier.setChecked(false);
            checkBoxTPa.setChecked(false);
            checkBoxTPl.setChecked(false);
            checkBoxOcc.setChecked(false);

            spinner.setSelection(0);

            radioGroup.clearCheck();
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                " Selectionné : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}