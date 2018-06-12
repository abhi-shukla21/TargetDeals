package me.abhishek.targetdeals.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import me.abhishek.targetdeals.model.Deal;

@Database(entities = {Deal.class}, version = 1)
public abstract class DealDatabase extends RoomDatabase {

    public static final String DB_NAME = "deal-db";

    public abstract DealDao dealDao();
}
