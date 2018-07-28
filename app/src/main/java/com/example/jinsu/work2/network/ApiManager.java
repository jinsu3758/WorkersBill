package com.example.jinsu.work2.network;


import com.example.jinsu.work2.BuildConfig;
import com.example.jinsu.work2.common.BaseApplication;
import com.example.jinsu.work2.manager.InfoManager;
import com.example.jinsu.work2.network.date.DateDeserializer;
import com.example.jinsu.work2.network.date.DateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.Locale;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiManager
{
//	public static final String HOST = "http://api.wbwb.kr";
	public static final String HOST = "http://api.wbwb.kr";
	public static final String ENDPOINT = "";

	public static String[][] mAvailableHostAddrList;

	public static String token = "";

	private static Gson gson = new GsonBuilder()
//            .serializeNulls()
			.registerTypeAdapter(Date.class, new DateDeserializer())
			.registerTypeAdapter(Date.class, new DateSerializer())
			.excludeFieldsWithoutExposeAnnotation()
			.create();

	private static RequestInterceptor requestInterceptor = new RequestInterceptor() {
		public void intercept(RequestFacade request) {
			token  = InfoManager.getOAuthToken();
			if (token.length() > 0) {
				request.addHeader("Authorization", "Bearer " + InfoManager.getOAuthToken());
			}
			request.addHeader("Content-Type", "application/json");
			request.addHeader("User-Agent", BaseApplication.getUserAgent());
			request.addHeader("Accept-Language", Locale.getDefault().getLanguage());
		}
	};

    public static RestAdapter restAdapter;

	public static ApiService rebuildAdapter(String url) {

		url += ENDPOINT;
		if (BuildConfig.DEBUG) {
			restAdapter = new RestAdapter.Builder()
					.setEndpoint(url)
					//.setClient(new OAuthClient())
					.setLogLevel(RestAdapter.LogLevel.BASIC)
					//.setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
					.setRequestInterceptor(requestInterceptor)
					.setConverter(new GsonConverter(gson))
					.build();
		} else {
			restAdapter = new RestAdapter.Builder()
					.setEndpoint(url)
					//.setClient(new OAuthClient())
					.setLogLevel(RestAdapter.LogLevel.NONE)
					.setRequestInterceptor(requestInterceptor)
					.setConverter(new GsonConverter(gson))
					.build();
		}
		return restAdapter.create(ApiService.class);
	}
}