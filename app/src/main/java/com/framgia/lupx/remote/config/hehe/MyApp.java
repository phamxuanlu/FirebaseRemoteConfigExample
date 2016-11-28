package com.framgia.lupx.remote.config.hehe;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * Created by pham.xuan.lu on 11/28/16.
 */

public class MyApp extends Application {
    // Khi chạy trong release mode thì config sẽ được cache tại app trong vòng 12 tiếng,
    // có nghĩa là nếu bạn thay đổi giá trị trên console thì có thể cần 12 tiếng mới có tác dụng
    // trên hết tất cả người dùng.
    private static final long CONFIG_EXPIRE_SECOND = 12 * 3600;

    @Override
    public void onCreate() {
        super.onCreate();

        //Get instance của remote config
        FirebaseRemoteConfig config = FirebaseRemoteConfig.getInstance();
        //Vứt nó vào 1 class singleton dùng cho tiện
        AppConfigs.getInstance().setConfig(config);
        //Setting chế độ debug
        FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG).build();
        config.setConfigSettings(settings);

        //FirebaseRemoteConfig sử dụng các giá trị defaule trong file R.xml.default_config nếu không lấy được giá trị
        config.setDefaults(R.xml.default_config);

        //Vì chúng ta đang trong debug mode nên cần config được fetch và active ngay lập tức sau khi thay đổi trên console
        long expireTime = config.getInfo().getConfigSettings().isDeveloperModeEnabled() ? 0 : CONFIG_EXPIRE_SECOND;
        //Mỗi lần khởi chạy app sẽ fetch config về và nếu thành công thì sẽ active config vừa lấy về
        config.fetch(expireTime)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AppConfigs.getInstance().getConfig().activateFetched();
                        }
                    }
                });
    }
}
