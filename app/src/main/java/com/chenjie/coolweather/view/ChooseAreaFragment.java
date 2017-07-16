package com.chenjie.coolweather.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenjie.coolweather.R;
import com.chenjie.coolweather.activity.MainActivity;
import com.chenjie.coolweather.activity.WeatherActivity;
import com.chenjie.coolweather.dao.City;
import com.chenjie.coolweather.dao.Country;
import com.chenjie.coolweather.dao.Province;
import com.chenjie.coolweather.utils.JSONUtil;
import com.chenjie.coolweather.utils.NetworkUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

;

/**
 * Created by chenjie on 2017/7/15.
 */

public class ChooseAreaFragment extends Fragment {

    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTRY = 2;

    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ProgressDialog progressDialog;

    private List<Province> provinceList;
    private List<City> cityList;
    private List<Country> countryList;

    private Province selectedProvince;
    private City selectedCity;
    private Country selectedCountry;

    private int currentLevel = LEVEL_PROVINCE;

    private List<String> dataList = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);


        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);

        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(i);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(i);
                    queryCountries();
                } else if (currentLevel == LEVEL_COUNTRY) {
                    selectedCountry = countryList.get(i);
                    String weatherID = selectedCountry.getWeatherID();

                    if (getActivity() instanceof MainActivity) {
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id", weatherID);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity) {
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        activity.getDrawerLayout().closeDrawers();
                        activity.getSwipeRefreshLayout().setRefreshing(true);
                        activity.requestWeather(weatherID);
                    }


                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                } else if (currentLevel == LEVEL_COUNTRY) {
                    queryCities();
                }
            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);

        queryProvinces();
    }

    private void queryProvinces() {
        titleText.setText(R.string.china);
        backButton.setVisibility(View.GONE);
        boolean successedFromDB = queryProvincesFromDB();
        if (!successedFromDB) {
            queryProvincesFromServer();
        }
    }

    private boolean queryProvincesFromDB() {
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
            return true;
        }
        return false;
    }

    private void queryProvincesFromServer() {
        progressDialog.show();
        String address = "http://guolin.tech/api/china";
        NetworkUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), R.string.load_provinces_from_server_failed, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final boolean result = JSONUtil.handleProvinceResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if (result) {
                            queryProvincesFromDB();
                        }
                    }
                });
            }
        });
    }

    private void queryCities() {
        titleText.setText(selectedProvince.getName());
        backButton.setVisibility(View.VISIBLE);
        boolean succesedFromDB = queryCitiesFromDB();
        if (!succesedFromDB) {
            queryCitiesFromServer();
        }
    }

    private boolean queryCitiesFromDB() {
        cityList = DataSupport.where("provinceid = ?", String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
            return true;
        }
        return false;
    }

    private void queryCitiesFromServer() {
        progressDialog.show();
        String address = "http://guolin.tech/api/china/" + selectedProvince.getCode();
        NetworkUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), R.string.load_cities_from_server_failed, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final boolean result = JSONUtil.handleCityResponse(responseText, selectedProvince.getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if (result) {
                            queryCitiesFromDB();
                        }
                    }
                });
            }
        });
    }

    private void queryCountries() {
        titleText.setText(selectedCity.getName());
        backButton.setVisibility(View.VISIBLE);
        boolean succesedFromDB = queryCountriesFromDB();
        if (!succesedFromDB) {
            queryCountriesFromServer();
        }
    }

    private boolean queryCountriesFromDB() {
        countryList = DataSupport.where("cityid = ?", String.valueOf(selectedCity.getId())).find(Country.class);
        if (countryList.size() > 0) {
            dataList.clear();
            for (Country country : countryList) {
                dataList.add(country.getName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTRY;
            return true;
        }
        return false;
    }

    private void queryCountriesFromServer() {
        progressDialog.show();
        String address = "http://guolin.tech/api/china/" + selectedProvince.getCode() + "/" + selectedCity.getCode();
        NetworkUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), R.string.load_country_from_server_failed, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                final boolean result = JSONUtil.handleCountryResponse(responseText, selectedCity.getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if (result) {
                            queryCountriesFromDB();
                        }
                    }
                });

            }
        });
    }
}
