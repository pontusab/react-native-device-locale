package com.reactnativedevicelocale;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.res.Configuration;
import java.util.TimeZone;
import java.util.Locale;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import android.os.Build;

public class DeviceLocaleModule extends ReactContextBaseJavaModule {
  public DeviceLocaleModule(ReactApplicationContext reactContext) {
    super(reactContext);
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

    constants.put("timezone", TimeZone.getDefault().getID());
    constants.put("deviceLocale", this.getCurrentLanguage());
    constants.put("deviceCountry", this.getCurrentCountry());
    constants.put("preferredLocales", this.getPreferredLocales());
    
    return constants;
  }
}