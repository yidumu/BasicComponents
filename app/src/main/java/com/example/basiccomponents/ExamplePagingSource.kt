package com.example.basiccomponents

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 *
 */
class ExamplePagingSource(
    //val backend: ExampleBackendService,
    //val query: String
) : PagingSource<Int, String>() {

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
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

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        Log.d("TAG", "load() called with: params = $params")
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        //val response = backend.searchUsers(query, nextPageNumber)
        val numbers = mutableListOf("one", "two", "three", "four")
        return LoadResult.Page(
            data = numbers,//response.users,
            prevKey = null, // Only paging forward.
            nextKey = null//下一个数据是什么
        )
    }
}