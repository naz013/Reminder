package com.elementary.tasks.google_tasks;

import com.google.gson.annotations.SerializedName;

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

public class RealmTaskList extends RealmObject {

    @SerializedName("title")
    private String title;
    @SerializedName("listId")
    @PrimaryKey
    private String listId;
    @SerializedName("def")
    private int def;
    @SerializedName("eTag")
    private String eTag;
    @SerializedName("kind")
    private String kind;
    @SerializedName("selfLink")
    private String selfLink;
    @SerializedName("updated")
    private long updated;
    @SerializedName("color")
    private int color;
    @SerializedName("systemDefault")
    private int systemDefault;

    public RealmTaskList() {
    }

    public RealmTaskList(TaskListItem item) {
        setColor(item.getColor());
        setTitle(item.getTitle());
        setListId(item.getListId());
        seteTag(item.geteTag());
        setKind(item.getKind());
        setSelfLink(item.getSelfLink());
        setUpdated(item.getUpdated());
        setDef(item.getDef());
        setSystemDefault(item.getSystemDefault());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSystemDefault() {
        return systemDefault;
    }

    public void setSystemDefault(int systemDefault) {
        this.systemDefault = systemDefault;
    }
}
