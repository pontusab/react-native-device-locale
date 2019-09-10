package com.reactnativedevicelocale;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import java.util.TimeZone;
import java.util.Locale;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import android.os.Build;

public class DeviceLocaleModule extends ReactContextBaseJavaModule {

  ReactApplicationContext reactContext;

  public DeviceLocaleModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "DeviceLocale";
  }

  private String getCurrentLanguage() {
    Locale current;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      current = getReactApplicationContext().getResources().getConfiguration().getLocales().get(0);
    } else {
      current = getReactApplicationContext().getResources().getConfiguration().locale;
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return current.toLanguageTag();
    } else {
      StringBuilder builder = new StringBuilder();
      builder.append(current.getLanguage());
      if (current.getCountry() != null) {
        builder.append("-");
        builder.append(current.getCountry());
      }
      return builder.toString();
    }
  }

   private String getCurrentCountry() {
    Locale current;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      current = getReactApplicationContext().getResources().getConfiguration().getLocales().get(0);
    } else {
      current = getReactApplicationContext().getResources().getConfiguration().locale;
    }

    return current.getCountry();
  }

  private ArrayList<String> getPreferredLocales() {
    Configuration configuration = getReactApplicationContext().getResources().getConfiguration();
    ArrayList<String> preferred = new ArrayList<>();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      for (int i = 0; i < configuration.getLocales().size(); i++) {
        preferred.add(configuration.getLocales().get(i).getLanguage());
      }
    } else {
      preferred.add(configuration.locale.getLanguage());
    }

    return preferred;
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();

    PackageManager packageManager = this.reactContext.getPackageManager();
    String packageName = this.reactContext.getPackageName();

    constants.put("timezone", TimeZone.getDefault().getID());
    constants.put("deviceLocale", this.getCurrentLanguage());
    constants.put("deviceCountry", this.getCurrentCountry());
    constants.put("preferredLocales", this.getPreferredLocales());

    constants.put("appVersion", "not available");
    constants.put("appName", "not available");
    constants.put("buildVersion", "not available");
    constants.put("buildNumber", 0);
    
    try {
      PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
      PackageInfo info = packageManager.getPackageInfo(packageName, 0);
      String applicationName = this.reactContext.getApplicationInfo().loadLabel(this.reactContext.getPackageManager()).toString();
      constants.put("appVersion", info.versionName);
      constants.put("buildNumber", info.versionCode);
      constants.put("appName", applicationName);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    return constants;
  }
}