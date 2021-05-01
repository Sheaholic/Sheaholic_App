package com.example.sheaholics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

public class SkillsSetup extends AppCompatActivity {
    Button mAdd,mSkip;
    String cat1,cat2;
    private DatabaseReference SkillsRef;
    private FirebaseAuth mAuth;
    String currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser().getUid();
        SkillsRef= FirebaseDatabase.getInstance().getReference().child(currentUserId).child("Skills");
        setContentView(R.layout.activity_skills_setup);
        mAdd=findViewById(R.id.add);
        mSkip=findViewById(R.id.skip);
        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
        final Spinner spinner2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(SkillsSetup.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.categories));
        final ArrayAdapter<String> adapter2=new ArrayAdapter<String>(SkillsSetup.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.sub1));
        final ArrayAdapter<String> adapter3=new ArrayAdapter<String>(SkillsSetup.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.sub2));
        final ArrayAdapter<String> adapter4=new ArrayAdapter<String>(SkillsSetup.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.sub3));
        final ArrayAdapter<String> adapter5=new ArrayAdapter<String>(SkillsSetup.this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.sub4));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();
                cat1 = label;
                if(label.equals("Web Development"))
                    spinner2.setAdapter(adapter2);
                else if(label.equals("App Development"))
                    spinner2.setAdapter(adapter3);
                else if(label.equals("Machine Learning"))
                    spinner2.setAdapter(adapter4);
                else
                    spinner2.setAdapter(adapter5);
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                cat2=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog materialDialog=new MaterialDialog.Builder(SkillsSetup.this)
                        .setTitle("Adding the skill "+cat1+":"+cat2)
                        .setMessage("The skill will only be considered when you will pass the quiz.Do you want to attempt the qui?")
                        .setCancelable(false)
                        .setPositiveButton("I'll attempt now!",R.drawable.photo_n, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                dialogInterface.dismiss();

                            }
                        })
                        .setNegativeButton("Later",R.drawable.photo_n, new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {

                                dialogInterface.dismiss();

                            }
                        })
                        .build();
                materialDialog.show();
            }

        });

    }
}