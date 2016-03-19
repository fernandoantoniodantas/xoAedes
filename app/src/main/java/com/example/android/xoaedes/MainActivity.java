package com.example.android.xoaedes;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class
        MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Iniciando com o Fragment Startup

        Fragment fragg = null;
        fragg = new FragmentStartup();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag, fragg);
        fragmentTransaction.commit();


        navigationView.setNavigationItemSelectedListener(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displayView(item.getItemId());
        return true;
    }

    // Mostra o Fragment do menu que foi selecionado
    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_dengue:
                fragment = new FragmentDengue();
                title = "Dengue";
                break;
            case R.id.nav_chikungunya:
                fragment = new FragmentChikungunya();
                title = "Chikungunya";
                break;
            case R.id.nav_zika:
                fragment = new FragmentZika();
                title = "Zika";
                break;
            case R.id.nav_prevencao:
                fragment = new FragmentPrevencao();
                title = "Prevenção/Proteção";
                break;
            case R.id.nav_principal:
                fragment = new FragmentStartup();
                title = "Combate ao Aedes";
                break;
            case R.id.nav_sobre:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_enviar:
                String conteudoDoEmail = email();
                Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
                intentEmail.setData(Uri.parse("mailto:"));
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Xô Aedes.");
                intentEmail.putExtra(Intent.EXTRA_TEXT, conteudoDoEmail);
                if (intentEmail.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentEmail);
                }
                break;
        }

        if (fragment != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frag, fragment);
            transaction.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    public String email() {
        //    Colocando as Strings do xml para o MainActivity
        String startUpTitle = getString(R.string.p_oque_e_aedes);
        String startUpContent = getString(R.string.r_oque_e_aedes);
        String zikaTitle = getString(R.string.p_oque_e_zika);
        String zikaContent = getString(R.string.r_oque_e_zika);
        String chikTitle = getString(R.string.p_oque_e_chikungunya);
        String chikContent = getString(R.string.r_oque_e_chikungunya);
        String dengueTitle = getString(R.string.p_oque_e_dengue);
        String dengueContent = getString(R.string.r_oque_e_dengue);
        String prevencaoTitle = getString(R.string.p_prevencao_protecao);
        String prevencaoContent = getString(R.string.r_prevencao_protecao);


        //   Concatenando todas as strings
        String conteudoDoEmail = startUpTitle + "\n\n" + startUpContent + "\n\n";
        conteudoDoEmail = conteudoDoEmail + zikaTitle + "\n\n" + zikaContent + "\n\n";
        conteudoDoEmail = conteudoDoEmail + chikTitle + "\n\n" + chikContent + "\n\n";
        conteudoDoEmail = conteudoDoEmail + dengueTitle + "\n\n" + dengueContent + "\n\n";
        conteudoDoEmail = conteudoDoEmail + prevencaoTitle + "\n\n" + prevencaoContent + "\n\n";
        conteudoDoEmail = conteudoDoEmail + "\nEsse email foi apenas um resumo. Para mais informações baixe o app"
                + " ou acesse: http://combateaedes.saude.gov.br/\n";

        return conteudoDoEmail;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.testeaedes/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.testeaedes/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
