package me.abhishek.targetdeals.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DealList {
    @SerializedName("_id")
    String _id;
    @SerializedName("data")
    List<Deal> data;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Deal> getData() {
        return data;
    }

    public void setData(List<Deal> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
}
