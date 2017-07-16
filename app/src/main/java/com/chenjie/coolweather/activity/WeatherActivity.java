package com.chenjie.coolweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chenjie.coolweather.R;
import com.chenjie.coolweather.model.Weather;
import com.chenjie.coolweather.service.AutoUpdateWeatherDataService;
import com.chenjie.coolweather.utils.JSONUtil;
import com.chenjie.coolweather.utils.NetworkUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = WeatherActivity.class.getName();

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carwashText;
    private TextView sportText;

    private ImageView backgroundImage;

    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private Button navButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_weather);

        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);

        forecastLayout = (LinearLayout) findViewById(R.id.daily_forecast_layout);

        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carwashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);

        backgroundImage = (ImageView) findViewById(R.id.weather_background_image);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navButton = (Button) findViewById(R.id.nav_button);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = pref.getString("weather", null);
        String bingPic = pref.getString("bing_pic", null);

        final String weatherID;
        if (weatherString != null) {
            Weather weather = JSONUtil.handleWeatherResponse(weatherString);
            weatherID = weather.getBasic().getWeatherID();
            showWeatherInfo(weather);
        } else {
            weatherID = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherID);
        }
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(backgroundImage);
        } else {
            loadBingPic();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherID);
            }
        });

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void showWeatherInfo(Weather weather) {
        String cityName = weather.getBasic().getCityName();
        String updateTime = weather.getBasic().getUpdate().getUpdateTime();
        String degree = weather.getNow().getTemperature() + "℃";
        String weatherInfo = weather.getNow().getMore().getInfo();

        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();

        for (Weather.DailyForecast forecast : weather.getDailyForecast()) {
            View view = LayoutInflater.from(this).inflate(R.layout.daily_forecast_item, forecastLayout, false);

            TextView dateText = (TextView) view.findViewById(R.id.daily_date_text);
            TextView infoText = (TextView) view.findViewById(R.id.daily_info_text);
            TextView maxText = (TextView) view.findViewById(R.id.daily_max_text);
            TextView minText = (TextView) view.findViewById(R.id.daily_min_text);

            dateText.setText(forecast.getDate());
            infoText.setText(forecast.getMore().getInfo());
            maxText.setText(forecast.getTemperature().getMax());
            minText.setText(forecast.getTemperature().getMin());

            forecastLayout.addView(view);
        }

//        if (weather.aqi != null) {
//            aqiText.setText(weather.aqi.city.aqi);
//            pm25Text.setText(weather.aqi.city.pm25);
//        }

        String comfort = "舒适度：" + weather.getSuggestion().getComfort().getInfo();
        String carWash = "洗车指数：" + weather.getSuggestion().getCarWash().getInfo();
        String sport = "运动建议：" + weather.getSuggestion().getSport().getInfo();

        comfortText.setText(comfort);
        carwashText.setText(carWash);
        sportText.setText(sport);

        weatherLayout.setVisibility(View.VISIBLE);

        //启动后台服务更新天气数据
        Intent intent = new Intent(this, AutoUpdateWeatherDataService.class);
        startService(intent);
    }

    public void requestWeather(String weatherID) {
        String weatherRequestURL = "http://guolin.tech/api/weather?cityid=" + weatherID + "&key=bc0418b57b2d4918819d3974ac1285d9";
        Log.d(TAG, weatherRequestURL);
        NetworkUtil.sendHttpRequest(weatherRequestURL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, R.string.load_weather_from_server_failed, Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = JSONUtil.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.getStatus())) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, R.string.load_weather_from_server_failed, Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    private void loadBingPic() {
        String requestBingPicURLString = "http://guolin.tech/api/bing_pic";
        NetworkUtil.sendHttpRequest(requestBingPicURLString, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(backgroundImage);
                    }
                });
            }
        });
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }


}
