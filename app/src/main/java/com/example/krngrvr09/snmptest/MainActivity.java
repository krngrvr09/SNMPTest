package com.example.krngrvr09.snmptest;

import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.friendlysnmp.FException;
import org.friendlysnmp.FriendlyAgent;
import org.snmp4j.SNMP4JSettings;
import org.snmp4j.smi.Sm
import org.snmp4j.smi.OID;
import org.snmp4j.smi.TimeTicks;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            SmiManager smiManager = new SmiManager("myLicenseKey", new File("myEmptyDirectory"));
            SNMP4JSettings.setOIDTextFormat(smiManager);
        
            SNMP4JSettings.setVariableTextFormat(smiManager);
            // If you need to disable full index formatting, then choose a different format below and uncomment the line:
            // smiManager.setOidFormat(OIDFormat.ObjectNameAndDecodedIndex4RoundTrip);
        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    OID sysDescr = new OID(".1.3.6.1.2.1.1.4.0");
                    Agent agent = null;
                    try {
                        agent = new Agent("0.0.0.0/2001");
                        agent.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //while(true) {
                    //System.out.println("Agent running...");
                    Toast.makeText(getApplicationContext(),"Agent running...",Toast.LENGTH_SHORT).show();//Thread.sleep(5000);
                    //}
//                    agent.unregisterManagedObject(agent.getSnmpv2MIB());

//                    agent.registerManagedObject(MOScalarFactory.createReadOnly(sysDescr, "lol"/*new TimeTicks(5000)*/));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

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
