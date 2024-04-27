package fr.pierrad.widgetdemo.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fr.pierrad.widgetdemo.Pokemon.ApiClient;
import fr.pierrad.widgetdemo.Pokemon.ApiService;
import fr.pierrad.widgetdemo.Pokemon.Pokemon;
import fr.pierrad.widgetdemo.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OnlineListWidget extends AppWidgetProvider {

    private List<Pokemon> pokemons = new ArrayList<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.online_list_widget);
        views.setRemoteAdapter(R.id.widget_listView, new Intent(context, OnlineListService.class));
        appWidgetManager.updateAppWidget(appWidgetId, views);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_listView);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        getData(context);

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    private void getData(Context context) {
        ApiService apiService = ApiClient.ApiConnection().create(ApiService.class);

        Call<List<Pokemon>> call = apiService.getTeam();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(@NonNull Call<List<Pokemon>> call, @NonNull Response<List<Pokemon>> response) {
                List<Pokemon> dataResponse = response.body();
                assert dataResponse != null;
                pokemons = dataResponse;

                OnlineListSingleton.getInstance().setPokemons(pokemons);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, OnlineListWidget.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_listView);
            }

            @Override
            public void onFailure(@NonNull Call<List<Pokemon>> call, @NonNull Throwable t) {
            }
        });
    }

}