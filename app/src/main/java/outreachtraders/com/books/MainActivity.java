package outreachtraders.com.books;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mLoadingProgress=(ProgressBar) findViewById(R.id.pb_loading);
        try{
            URL bookUrl=ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);

            //new BooksQueryTask().execute(bookUrl);

        }
        catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }
    public class BooksQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL=urls[0];
            String result =null;
            try{
                result=ApiUtil.getJson(searchURL);
            }
            catch (IOException e){
                Log.e("Error", e.getMessage());
            }
            return result;
        }


        protected void onPostExecute(String result){
            TextView tvResult=(TextView) findViewById(R.id.tvResponse);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            tvResult.setText(result);
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}
