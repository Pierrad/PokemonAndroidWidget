package fr.pierrad.widgetdemo.Widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.pierrad.widgetdemo.Pokemon.Pokemon;
import fr.pierrad.widgetdemo.R;

public class OnlineListDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Intent intent;
    private List<Pokemon> pokemons = new ArrayList<>();

    public OnlineListDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        pokemons = OnlineListSingleton.getInstance().getPokemons();
    }

    @Override
    public void onDataSetChanged() {
        pokemons = OnlineListSingleton.getInstance().getPokemons();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.online_list_widget_item);
        remoteViews.setTextViewText(R.id.online_list_widget_item_text, pokemons.get(i).getName());
        System.out.println(pokemons.get(i).getName());
        System.out.println(pokemons.get(i).getImage());
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(pokemons.get(i).getImage()).getContent());
            remoteViews.setImageViewBitmap(R.id.online_list_widget_item_image, bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}