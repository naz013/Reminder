package com.elementary.tasks.core.contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog;

import com.elementary.tasks.core.utils.Contacts;
import com.elementary.tasks.core.utils.ContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2016 Nazar Suhovich
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class CallsAsync extends AsyncTask<Void, Void, List<CallsItem>> {

    private ContextHolder mContext;
    private CallsLogListener mListener;

    CallsAsync(Context context, CallsLogListener listener) {
        this.mContext = new ContextHolder(context);
        this.mListener = listener;
    }

    @Override
    protected List<CallsItem> doInBackground(Void... params) {
        List<CallsItem> mList = new ArrayList<>();
        try {
            Cursor c = mContext.getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
            if (c != null) {
                int number = c.getColumnIndex(CallLog.Calls.NUMBER);
                int type = c.getColumnIndex(CallLog.Calls.TYPE);
                int date = c.getColumnIndex(CallLog.Calls.DATE);
                int nameIndex = c.getColumnIndex(CallLog.Calls.CACHED_NAME);
                while (c.moveToNext()) {
                    String phoneNumber = c.getString(number);
                    String callType = c.getString(type);
                    String callDate = c.getString(date);
                    String name = c.getString(nameIndex);
                    int id = Contacts.getIdFromNumber(phoneNumber, mContext.getContext());
                    String photo = null;
                    if (id != 0) {
                        Uri uri = Contacts.getPhoto(id);
                        if (uri != null) {
                            photo = uri.toString();
                        }
                    }

                    CallsItem data = new CallsItem(name, phoneNumber, photo, Long.valueOf(callDate), id, Integer.parseInt(callType));
                    int pos = getPosition(data.getDate(), mList);
                    if (pos == -1) {
                        mList.add(data);
                    } else {
                        mList.add(pos, data);
                    }
                }
                c.close();
            }
        } catch (SecurityException ignored) {
        }
        return mList;
    }

    private int getPosition(long date, List<CallsItem> list) {
        if (list.size() == 0) {
            return 0;
        }
        int position = -1;
        for (CallsItem data : list) {
            if (date > data.getDate()) {
                position = list.indexOf(data);
                break;
            }
        }
        return position;
    }

    @Override
    protected void onPostExecute(List<CallsItem> list) {
        super.onPostExecute(list);
        if (mListener != null) {
            mListener.onLoaded(list);
        }
    }
}
