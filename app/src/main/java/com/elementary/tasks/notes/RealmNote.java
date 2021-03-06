package com.elementary.tasks.notes;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

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

public class RealmNote extends RealmObject {
    @SerializedName("summary")
    private String summary;
    @SerializedName("key")
    @PrimaryKey
    private String key;
    @SerializedName("date")
    private String date;
    @SerializedName("color")
    private int color;
    @SerializedName("style")
    private int style;
    @SerializedName("images")
    private RealmList<RealmImage> images = new RealmList<>();
    @SerializedName("uniqueId")
    private int uniqueId;

    public RealmNote() {

    }

    public RealmNote(NoteItem item) {
        setColor(item.getColor());
        setDate(item.getDate());
        this.images = new RealmList<>();
        for (NoteImage image : item.getImages()) {
            images.add(new RealmImage(image));
        }
        setKey(item.getKey());
        setStyle(item.getStyle());
        setSummary(item.getSummary());
        setUniqueId(item.getUniqueId());
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public RealmList<RealmImage> getImages() {
        return images;
    }

    public void setImages(RealmList<RealmImage> images) {
        this.images = images;
    }
}
