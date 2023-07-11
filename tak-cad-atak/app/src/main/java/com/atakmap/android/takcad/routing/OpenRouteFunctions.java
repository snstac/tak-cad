package com.atakmap.android.takcad.routing;

import static com.atakmap.android.takcad.routing.OpenRouteConstants.OPEN_ROUTE_API_KEY;
import static com.atakmap.android.takcad.routing.OpenRouteConstants.OPEN_ROUTE_API_KEY_ARG;
import static com.atakmap.android.takcad.routing.OpenRouteConstants.OPEN_ROUTE_DIRECTIONS_GET_URL;
import static com.atakmap.android.takcad.routing.OpenRouteConstants.OPEN_ROUTE_END_ARG;
import static com.atakmap.android.takcad.routing.OpenRouteConstants.OPEN_ROUTE_PROFILE_DRIVING_CAR;
import static com.atakmap.android.takcad.routing.OpenRouteConstants.OPEN_ROUTE_START_ARG;

import com.atakmap.coremap.log.Log;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenRouteFunctions {

    public static String TAG = OpenRouteFunctions.class.getSimpleName();

    public static void getDirections(String long1, String lat1, String long2, String lat2,
                                     OpenRouteDirectionResponse callback) {

        Log.d(TAG, "Trying to get directions from point to point...");

        Thread networkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder
                            = HttpUrl.parse(
                            OPEN_ROUTE_DIRECTIONS_GET_URL +
                                    OPEN_ROUTE_PROFILE_DRIVING_CAR
                    ).newBuilder();
                    urlBuilder.addQueryParameter(OPEN_ROUTE_API_KEY_ARG, OPEN_ROUTE_API_KEY);
                    urlBuilder.addQueryParameter(OPEN_ROUTE_START_ARG, lat1 + "," + long1);
                    urlBuilder.addQueryParameter(OPEN_ROUTE_END_ARG, lat2 + "," + long2);

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Call call = client.newCall(request);
                    Response response = call.execute();
                    String body = response.body().string();

                    Log.d(TAG, "Got response: " + response);
                    Log.d(TAG, "Got response body: " + body);

                    ObjectMapper om = new ObjectMapper();
                    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    DirectionsResponsePojos.Root root = om.readValue(body, DirectionsResponsePojos.Root.class);

                    Log.d(TAG, "Parsed directions response: " + root);

                    callback.processDirections(root);

                } catch (IOException e) {
                    Log.e(TAG, "Exception with http get: " + e, e);
                }
            }
        });

        networkThread.start();
    }

}
