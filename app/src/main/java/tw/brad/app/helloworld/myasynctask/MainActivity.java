package tw.brad.app.helloworld.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;
    private TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        tv2 = (TextView)findViewById(R.id.tv2);
    }
    public void test1(View view){
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("Brad", "III", "OK", "Game", "IOS");
    }
    public void test2(View view){
        tv.setText("Lottery: " + (int)(Math.random()*49+1));
    }
    public void test3(View view){
        if (myAsyncTask!=null && !myAsyncTask.isCancelled()){
            myAsyncTask.cancel(true);
        }
    }

    private class MyAsyncTask extends AsyncTask<String,Integer,String>{
        int i;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("brad", "onPreExecute");
        }

        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);
            tv.setText(ret);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv2.setText(values[0] + " : " + values[1] + " : " + values[2]);
        }

        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);
            tv.setText(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("brad", "onCancelled");
        }

        @Override
        protected String doInBackground(String... names) {
            String result = "OK";
            for (String name : names){
                i++;
                Log.i("brad", name);
                publishProgress(i, i*10, i*100);
                if (isCancelled()){
                    result = "NOT OK";
                    break;
                }
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
            return result;
        }
    }

}
