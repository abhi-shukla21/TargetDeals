package me.abhishek.targetdeals.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.abhishek.targetdeals.model.Deal;

@Dao
public interface DealDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Deal> deals);

    @Query("SELECT * FROM deal where _id = :dealId")
    LiveData<Deal> get(String dealId);

    @Query("SELECT * FROM deal")
    LiveData<List<Deal>> getAll();
}
