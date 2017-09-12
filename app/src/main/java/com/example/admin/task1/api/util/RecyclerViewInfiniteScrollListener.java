package com.example.admin.task1.api.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by Admin on 9/11/2017.
 */

public abstract class RecyclerViewInfiniteScrollListener extends RecyclerView.OnScrollListener {
    //http://www.avocarrot.com/blog/implement-infinitely-scrolling-list-android/
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 2;
    private int currentPage = 0;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;

    public RecyclerViewInfiniteScrollListener(LinearLayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            loadMore((currentPage + 1));
            loading = true;
        }
    }

    public abstract void loadMore(int page);
}