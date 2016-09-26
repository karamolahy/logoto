package com.example.radicloud.logoto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.radicloud.logoto.Models.Person;

import java.io.File;
import java.io.IOException;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.Address;
import ezvcard.property.Email;
import ezvcard.property.FormattedName;
import ezvcard.property.Organization;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepage = new Intent(MainActivity.this, WriteTagActivity.class);
                startActivity(homepage);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });
        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context= getApplicationContext();
                Toast.makeText(context,"its working",Toast.LENGTH_SHORT).show();

                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            }
        });
        Button button2 = (Button) findViewById(R.id.resume);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveDb();

                Context context= getApplicationContext();
                Toast.makeText(context,"its working",Toast.LENGTH_SHORT).show();
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
        });
    }
    // clicking next button
    public void saveDb(){
        // save person on db
        Person person= new Person();

        //Person person;
        EditText txtName=(EditText) findViewById(R.id.editText);
        person.setName(txtName.getText().toString());
        EditText txtPhone=(EditText) findViewById(R.id.phone);
        person.setPhone(txtPhone.getText().toString());
        EditText txtEmail=(EditText) findViewById(R.id.email);
        person.setEmail(txtEmail.getText().toString());
        EditText txtMobile=(EditText) findViewById(R.id.mobile);
        person.setMobile(txtMobile.getText().toString());
        EditText txtAdd=(EditText) findViewById(R.id.add);
        person.setAddress(txtAdd.getText().toString());
        EditText txtFax=(EditText) findViewById(R.id.fax);
        person.setFax(txtFax.getText().toString());
        EditText txtJob=(EditText) findViewById(R.id.job);
        person.setJobDesc(txtJob.getText().toString());
        String vcf= generateVcf(person);
        Toast.makeText(MainActivity.this,vcf,Toast.LENGTH_LONG).show();



    }

    public String generateVcf(Person p) {
        File vcfFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "generated.vcf");

        VCard vcard = new VCard();
        vcard.setVersion(VCardVersion.V3_0);

        StructuredName n = new StructuredName();
        n.setFamily(p.getFname());
        n.setGiven(p.getName());
        vcard.setStructuredName(n);
        vcard.setFormattedName(new FormattedName(p.getFname() + " " + p.getName()));

        Organization org = new Organization();
        org.addParameter("",p.getJobDesc());
        vcard.setOrganization(org);

        Address add=new Address();
        add.addParameter("",p.getAddress());
        vcard.addAddress(add);






        //vcard.addTitle(new TitleType(p.getTitle()));

        Telephone tel = new Telephone(p.getPhone());
        vcard.addTelephoneNumber(tel);

        tel = new Telephone(p.getFax());
        vcard.addTelephoneNumber(tel);

        Address adr = new Address();
        Email email = new Email(p.getEmail());
        vcard.addEmail(email);
        // Geo geo=new Geo(p.getGeoTag());

        //vcard.addPhoto();

        try {
            vcard.write(vcfFile);
            System.out.println(vcfFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vcfFile.getPath();


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
