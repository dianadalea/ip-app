package com.example.ip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    TextView content;
   // TextView textTest;
    EditText User;
    EditText Pass;
    String userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User = (EditText) findViewById(R.id.user);
        Pass =(EditText) findViewById(R.id.pass);
        Button login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                try{

                    // CALL GetText method to make post method call
                    GetText();
                }
                catch(Exception ex)
                {
                    content.setText(" url exeption! " );
                }
            }
        });
    }
    // Create GetText Metod
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values
        userName = User.getText().toString();
        password   = Pass.getText().toString();

        // Create data variable for sent values to server

        String data = URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(userName, "UTF-8");



        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(password, "UTF-8");

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://192.168.137.1:5090/api/Auth/LogIn");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {

                // Append server response in string
                sb.append(line + "\n");
            }



            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        content.setText( text  );

    }

}

       // textTest = (TextView) findViewById(R.id.textTest);

  //  }

    //public void Onclick (View view) {

        //textTest.setText("Buna Diana!");
  //  }

//    public void changeText (View view) {
  //      Button clicked = (Button) view;

    //    textTest.setText("Buna Diana!");
    //}

   // public void clickText (View text) {
    //    TextView tv = (TextView)text;

 //       tv.setText("Test Click");
  //  }



  //  public void nextAct(View view)
 //   {
  //      Intent intent = new Intent(this, Activity2.class);



 //       String message = textTest.getText().toString();
  //      intent.putExtra("com.example.ip.MESSAGE1", message);
  //      startActivity(intent);
 //   }
//}
