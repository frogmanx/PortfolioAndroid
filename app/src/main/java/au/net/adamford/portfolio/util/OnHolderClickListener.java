package au.net.adamford.portfolio.util;

import android.view.View;

/**
 * Created by Adam on 17/11/2015.
 */
public interface OnHolderClickListener<T> {
    void onClick(T object, View itemView);
}