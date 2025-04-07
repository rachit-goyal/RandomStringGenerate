package com.example.randomstringgenerate.component.util

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.example.randomstringgenerate.data.model.RandomString
import org.json.JSONObject
/**
created by Rachit on 4/7/2025.
 */
object ContentProviderHelper {

    private const val TAG = "ContentProviderHelper"
    private const val AUTHORITY = "com.iav.contestdataprovider"
    private const val PATH = "text"
    private const val DATA_URI = "content://$AUTHORITY/$PATH"
    private const val COLUMN_NAME = "data" // important: actual column name

    fun fetchRandomString(context: Context, maxLength: Int): RandomString? {
        return try {
            val uri = Uri.parse(DATA_URI)

            val args = Bundle().apply {
                putInt(
                    "maxLength",
                    maxLength
                )
                putInt(android.content.ContentResolver.QUERY_ARG_LIMIT, maxLength)
            }

            val cursor = context.contentResolver.query(
                uri,
                null,
                args,
                null
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    val jsonStr = it.getString(it.getColumnIndexOrThrow(COLUMN_NAME))

                    val json = JSONObject(jsonStr).getJSONObject("randomText")

                    return RandomString(
                        value = json.getString("value"),
                        length = json.getInt("length"),
                        created = json.getString("created")
                    )
                } else {
                    Log.e(TAG, "Cursor empty")
                    null
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Missing READ permission!", e)
            null
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching string", e)
            null
        }
    }
}