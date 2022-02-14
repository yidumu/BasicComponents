package com.example.basiccomponents

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

/**
 *
 */
class ExamplePagingSource() : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        // Start refresh at page 1 if undefined.
        return try {
            val nextPageNumber = params.key ?: 1//当key为null return 1
            var numbers = mutableListOf(
                User(10), User(11), User(13), User(14), User(15), User(16),
                User(17), User(18)
            )
            Log.d("TAG", "load() called with: params = ${params.key} $numbers")
            LoadResult.Page(
                data = numbers,//response.users,
                prevKey = null, // Only paging forward.
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        Log.d("TAG", "getRefreshKey() called with: state = $state")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}