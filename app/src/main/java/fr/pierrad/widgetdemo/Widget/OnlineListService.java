package fr.pierrad.widgetdemo.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class OnlineListService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new OnlineListDataProvider(this, intent);
    }
}
