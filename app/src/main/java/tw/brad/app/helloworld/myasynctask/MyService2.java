package tw.brad.app.helloworld.myasynctask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {
    private MediaPlayer mp;

    public MyService2() {
        Log.i("brad", "MySrvice2()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("brad", "onBind2");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.try_everything);

        int len = mp.getDuration();
        Intent it = new Intent("brad");
        it.putExtra("len", len);
        sendBroadcast(it);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isStart = intent.getBooleanExtra("start", false);
        boolean isPause = intent.getBooleanExtra("pause", false);

        if (isStart && mp != null && !mp.isPlaying()){
            mp.start();
        }
        if (isPause && mp != null && mp.isPlaying()){
            mp.pause();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null){
            if (mp.isPlaying()) mp.stop();
            mp.release();
            //mp.reset();
            mp = null;
        }
    }
}
