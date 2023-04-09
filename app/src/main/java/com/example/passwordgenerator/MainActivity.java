package com.example.passwordgenerator;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static String chars = "AZERTYUIOPQSDFGHJKLMWXCVBN?./§¨%£µ1234567890°+<azertyuiop^$*qsdfghjklmùwxcvbn,;:!";
    static String normalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

    int numberOfCharacters;


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // on below line we are displaying toast message for selected item
        Toast.makeText(MainActivity.this, "" + numbers[position] + " Selected..", Toast.LENGTH_SHORT).show();
        String selectedItem = parent.getItemAtPosition(position).toString();
        numberOfCharacters = Integer.parseInt(selectedItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGeneratePassword = findViewById(R.id.btnGeneratePasswordSpecials);
        TextView txtGeneratedPasswordSpecials = findViewById(R.id.textPasswordSpecials);
        Spinner spinnerPasswordLength = findViewById(R.id.spinnerPasswordLength);




        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPasswordLength.setOnItemSelectedListener(this);
        spinnerPasswordLength.setAdapter(adapter);
        int selection = parseInt(spinnerPasswordLength.getSelectedItem().toString());
        int spinnerPosition = adapter.getPosition(selection);
        spinnerPasswordLength.setSelection(spinnerPosition);


        btnGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logique de traitement à exécuter lorsque le bouton est cliqué
                String generatedSpecialsPassword = generatePassword(numberOfCharacters);
                txtGeneratedPasswordSpecials.setText("Mot de passe généré : " + generatedSpecialsPassword);
                // Copier-coller
                String generatedPassword = txtGeneratedPasswordSpecials.getText().toString(); // Récupérer le texte du TextView (le mot de passe généré)
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); // Créer un gestionnaire de presse-papiers
                ClipData clip = ClipData.newPlainText("Mot de passe généré", generatedSpecialsPassword); // Créer un ClipData contenant le mot de passe généré
                clipboard.setPrimaryClip(clip); // Copier le ClipData dans le presse-papiers
                Toast.makeText(MainActivity.this, "Mot de passe copié", Toast.LENGTH_SHORT).show(); // Afficher un message pour indiquer que le mot de passe a été copié

            }
        });


        Button btnGenerateNormalPassword = findViewById(R.id.btnGeneratePassword);

        TextView txtGeneratedPassword = findViewById(R.id.textPassword);

        btnGenerateNormalPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logique de traitement à exécuter lorsque le deuxième bouton est cliqué
                String generatedNormalPassword = generateNormalCharsPassword(numberOfCharacters); // Appeler la fonction genereateNormalCharsPassword() pour générer le mot de passe normal
                txtGeneratedPassword.setText("Mot de passe généré : " + generatedNormalPassword); // Définir le contenu du deuxième TextView avec le mot de passe généré

                // Copier-coller
                String generatedPassword = txtGeneratedPassword.getText().toString(); // Récupérer le texte du TextView (le mot de passe généré)
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); // Créer un gestionnaire de presse-papiers
                ClipData clip = ClipData.newPlainText("Mot de passe généré", generatedNormalPassword); // Créer un ClipData contenant le mot de passe généré
                clipboard.setPrimaryClip(clip); // Copier le ClipData dans le presse-papiers
                Toast.makeText(MainActivity.this, "Mot de passe copié", Toast.LENGTH_SHORT).show(); // Afficher un message pour indiquer que le mot de passe a été copié

            }
        });
    }




    public static String generatePassword(int length) {
        int charsLength = chars.length();

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charsLength);
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public static String generateNormalCharsPassword(int length) {
        int normalCharsLength = normalChars.length();

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(normalCharsLength);
            sb.append(normalChars.charAt(index));
        }
        return sb.toString();

    }

    public static void main(String[] args) {

    }


}