package com.chenjie.coolweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chenjie on 2017/7/16.
 */

public class Weather {
    /**
     * basic : {"city":"彰化","cnty":"中国","id":"CN101340403","lat":"24.07699966","lon":"120.53500366","update":{"loc":"2017-07-16 15:49","utc":"2017-07-16 07:49"}}
     * daily_forecast : [{"astro":{"mr":"23:41","ms":"11:28","sr":"05:19","ss":"18:48"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2017-07-16","hum":"75","pcpn":"1.4","pop":"92","pres":"1011","tmp":{"max":"34","min":"26"},"uv":"12","vis":"15","wind":{"deg":"283","dir":"北风","sc":"4-5","spd":"17"}},{"astro":{"mr":"07:43","ms":"12:26","sr":"05:20","ss":"18:47"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2017-07-17","hum":"74","pcpn":"1.1","pop":"92","pres":"1013","tmp":{"max":"32","min":"26"},"uv":"5","vis":"18","wind":{"deg":"281","dir":"北风","sc":"4-5","spd":"19"}},{"astro":{"mr":"00:23","ms":"13:27","sr":"05:20","ss":"18:47"},"cond":{"code_d":"302","code_n":"305","txt_d":"雷阵雨","txt_n":"小雨"},"date":"2017-07-18","hum":"76","pcpn":"0.0","pop":"0","pres":"1014","tmp":{"max":"34","min":"26"},"uv":"12","vis":"19","wind":{"deg":"278","dir":"西北风","sc":"4-5","spd":"23"}},{"astro":{"mr":"01:09","ms":"14:29","sr":"05:21","ss":"18:47"},"cond":{"code_d":"302","code_n":"101","txt_d":"雷阵雨","txt_n":"多云"},"date":"2017-07-19","hum":"79","pcpn":"0.7","pop":"37","pres":"1011","tmp":{"max":"34","min":"25"},"uv":"11","vis":"18","wind":{"deg":"213","dir":"西北风","sc":"3-4","spd":"13"}},{"astro":{"mr":"01:59","ms":"15:33","sr":"05:21","ss":"18:46"},"cond":{"code_d":"302","code_n":"305","txt_d":"雷阵雨","txt_n":"小雨"},"date":"2017-07-20","hum":"77","pcpn":"0.3","pop":"79","pres":"1011","tmp":{"max":"33","min":"26"},"uv":"11","vis":"18","wind":{"deg":"216","dir":"西北风","sc":"4-5","spd":"19"}},{"astro":{"mr":"02:54","ms":"16:37","sr":"05:22","ss":"18:46"},"cond":{"code_d":"302","code_n":"305","txt_d":"雷阵雨","txt_n":"小雨"},"date":"2017-07-21","hum":"79","pcpn":"1.5","pop":"94","pres":"1013","tmp":{"max":"34","min":"26"},"uv":"6","vis":"19","wind":{"deg":"226","dir":"西北风","sc":"4-5","spd":"20"}},{"astro":{"mr":"03:54","ms":"17:39","sr":"05:22","ss":"18:46"},"cond":{"code_d":"302","code_n":"305","txt_d":"雷阵雨","txt_n":"小雨"},"date":"2017-07-22","hum":"80","pcpn":"2.2","pop":"88","pres":"1014","tmp":{"max":"34","min":"26"},"uv":"6","vis":"18","wind":{"deg":"229","dir":"西北风","sc":"4-5","spd":"19"}}]
     * hourly_forecast : [{"cond":{"code":"302","txt":"雷阵雨"},"date":"2017-07-16 16:00","hum":"71","pop":"17","pres":"1010","tmp":"31","wind":{"deg":"332","dir":"西北风","sc":"微风","spd":"13"}},{"cond":{"code":"300","txt":"阵雨"},"date":"2017-07-16 19:00","hum":"78","pop":"17","pres":"1011","tmp":"29","wind":{"deg":"315","dir":"西北风","sc":"微风","spd":"5"}},{"cond":{"code":"305","txt":"小雨"},"date":"2017-07-16 22:00","hum":"80","pop":"35","pres":"1011","tmp":"28","wind":{"deg":"258","dir":"西南风","sc":"微风","spd":"6"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"39","hum":"76","pcpn":"0.00","pres":"1009","tmp":"31","vis":"10","wind":{"deg":"340","dir":"北风","sc":"微风","spd":"7"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"},"cw":{"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"},"drsg":{"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较适宜","txt":"天气较好，但因气温较高且风力较强，请适当降低运动强度并注意户外防风。"},"trav":{"brf":"适宜","txt":"天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    private AQI aqi;
    private BasicBean basic;
    private NowBean now;
    private String status;
    private SuggestionBean suggestion;
    @SerializedName("daily_forecast")
    private List<DailyForecast> dailyForecast;
    @SerializedName("hourly_forecast")
    private List<HourlyForecastBean> hourlyForecast;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecast> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecast> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public List<HourlyForecastBean> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecastBean> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public static class AQI {

        private City city;

        public static class City {

            private String aqi; //空气质量指数
            private String co; //一氧化碳
            private String no2; //二氧化氮
            private String o3; //臭氧
            private String pm10; //PM10
            private String pm25; //PM2.5
            private String qlty; //质量评级
            private String so2; //二氧化硫

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }
    }

    public static class BasicBean {
        /**
         * city : 彰化
         * cnty : 中国
         * id : CN101340403
         * lat : 24.07699966
         * lon : 120.53500366
         * update : {"loc":"2017-07-16 15:49","utc":"2017-07-16 07:49"}
         */

        @SerializedName("city")
        private String cityName;
        private String cnty;
        @SerializedName("id")
        private String weatherID;
        private String lat;
        private String lon;
        private UpdateBean update;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getWeatherID() {
            return weatherID;
        }

        public void setWeatherID(String weatherID) {
            this.weatherID = weatherID;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public static class UpdateBean {
            /**
             * loc : 2017-07-16 15:49
             * utc : 2017-07-16 07:49
             */

            @SerializedName("loc")
            private String updateTime;
            private String utc;

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }

    public static class NowBean {
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 39
         * hum : 76
         * pcpn : 0.00
         * pres : 1009
         * tmp : 31
         * vis : 10
         * wind : {"deg":"340","dir":"北风","sc":"微风","spd":"7"}
         */

        @SerializedName("cond")
        private CondBean more;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        @SerializedName("tmp")
        private String temperature;
        private String vis;
        private WindBean wind;

        public CondBean getMore() {
            return more;
        }

        public void setMore(CondBean more) {
            this.more = more;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }


        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class CondBean {
            /**
             * code : 101
             * txt : 多云
             */

            private String code;
            @SerializedName("txt")
            private String info;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class WindBean {
            /**
             * deg : 340
             * dir : 北风
             * sc : 微风
             * spd : 7
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class SuggestionBean {
        /**
         * comf : {"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"}
         * cw : {"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"}
         * drsg : {"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
         * sport : {"brf":"较适宜","txt":"天气较好，但因气温较高且风力较强，请适当降低运动强度并注意户外防风。"}
         * trav : {"brf":"适宜","txt":"天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！"}
         * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
         */

        @SerializedName("comf")
        private Comfort comfort;
        @SerializedName("cw")
        private CarWash carWash;
        private DrsgBean drsg;
        private FluBean flu;
        private Sport sport;
        private TravBean trav;
        private UvBean uv;

        public Comfort getComfort() {
            return comfort;
        }

        public void setComfort(Comfort comfort) {
            this.comfort = comfort;
        }

        public CarWash getCarWash() {
            return carWash;
        }

        public void setCarWash(CarWash carWash) {
            this.carWash = carWash;
        }

        public DrsgBean getDrsg() {
            return drsg;
        }

        public void setDrsg(DrsgBean drsg) {
            this.drsg = drsg;
        }

        public FluBean getFlu() {
            return flu;
        }

        public void setFlu(FluBean flu) {
            this.flu = flu;
        }

        public Sport getSport() {
            return sport;
        }

        public void setSport(Sport sport) {
            this.sport = sport;
        }

        public TravBean getTrav() {
            return trav;
        }

        public void setTrav(TravBean trav) {
            this.trav = trav;
        }

        public UvBean getUv() {
            return uv;
        }

        public void setUv(UvBean uv) {
            this.uv = uv;
        }

        public static class Comfort {
            /**
             * brf : 较舒适
             * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
             */

            private String brf;
            @SerializedName("txt")
            private String info;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class CarWash {
            /**
             * brf : 较不宜
             * txt : 较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。
             */

            private String brf;
            @SerializedName("txt")
            private String info;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class DrsgBean {
            /**
             * brf : 炎热
             * txt : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
             */

            private String brf;
            @SerializedName("txt")
            private String info;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class FluBean {
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class Sport {
            /**
             * brf : 较适宜
             * txt : 天气较好，但因气温较高且风力较强，请适当降低运动强度并注意户外防风。
             */

            private String brf;
            @SerializedName("txt")
            private String info;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class TravBean {
            /**
             * brf : 适宜
             * txt : 天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class UvBean {
            /**
             * brf : 弱
             * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
             */

            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class DailyForecast {
        /**
         * astro : {"mr":"23:41","ms":"11:28","sr":"05:19","ss":"18:48"}
         * cond : {"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"}
         * date : 2017-07-16
         * hum : 75
         * pcpn : 1.4
         * pop : 92
         * pres : 1011
         * tmp : {"max":"34","min":"26"}
         * uv : 12
         * vis : 15
         * wind : {"deg":"283","dir":"北风","sc":"4-5","spd":"17"}
         */

        private AstroBean astro;
        @SerializedName("cond")
        private More more;
        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        @SerializedName("tmp")
        private Temperature temperature;
        private String uv;
        private String vis;
        private WindBeanX wind;

        public AstroBean getAstro() {
            return astro;
        }

        public void setAstro(AstroBean astro) {
            this.astro = astro;
        }


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public More getMore() {
            return more;
        }

        public void setMore(More more) {
            this.more = more;
        }

        public Temperature getTemperature() {
            return temperature;
        }

        public void setTemperature(Temperature temperature) {
            this.temperature = temperature;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBeanX getWind() {
            return wind;
        }

        public void setWind(WindBeanX wind) {
            this.wind = wind;
        }

        public static class AstroBean {
            /**
             * mr : 23:41
             * ms : 11:28
             * sr : 05:19
             * ss : 18:48
             */

            private String mr;
            private String ms;
            private String sr;
            private String ss;

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
        }

        public static class More {
            /**
             * code_d : 101
             * code_n : 104
             * txt_d : 多云
             * txt_n : 阴
             */

            private String code_d;
            private String code_n;
            @SerializedName("txt_d")
            private String info;
            private String txt_n;

            public String getCode_d() {
                return code_d;
            }

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getTxt_n() {
                return txt_n;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }
        }

        public static class Temperature {
            /**
             * max : 34
             * min : 26
             */

            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

        public static class WindBeanX {
            /**
             * deg : 283
             * dir : 北风
             * sc : 4-5
             * spd : 17
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class HourlyForecastBean {
        /**
         * cond : {"code":"302","txt":"雷阵雨"}
         * date : 2017-07-16 16:00
         * hum : 71
         * pop : 17
         * pres : 1010
         * tmp : 31
         * wind : {"deg":"332","dir":"西北风","sc":"微风","spd":"13"}
         */

        private CondBeanXX cond;
        private String date;
        private String hum;
        private String pop;
        private String pres;
        @SerializedName("tmp")
        private String temperature;
        private WindBeanXX wind;

        public CondBeanXX getCond() {
            return cond;
        }

        public void setCond(CondBeanXX cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public WindBeanXX getWind() {
            return wind;
        }

        public void setWind(WindBeanXX wind) {
            this.wind = wind;
        }

        public static class CondBeanXX {
            /**
             * code : 302
             * txt : 雷阵雨
             */

            private String code;
            @SerializedName("txt")
            private String info;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class WindBeanXX {
            /**
             * deg : 332
             * dir : 西北风
             * sc : 微风
             * spd : 13
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public AQI getAqi() {
        return aqi;
    }

    public void setAqi(AQI aqi) {
        this.aqi = aqi;
    }
}