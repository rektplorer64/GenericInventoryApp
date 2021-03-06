package io.rektplorer.inventoryapp.roomdatabase.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.rektplorer.inventoryapp.roomdatabase.Entities.Item;

@Dao
public interface ItemDAO{

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getAll();

    @Query("SELECT * FROM items WHERE name LIKE :name LIMIT 1")
    LiveData<Item> getItemByName(String name);

    @Query("SELECT * FROM items WHERE id LIKE :itemId LIMIT 1")
    LiveData<Item> getItemById(int itemId);

    @Query("SELECT MIN(id) FROM items")
    int getMinItemId();

    @Query("SELECT MAX(quantity) FROM items")
    int getMaxItemQuantity();

    @Query("SELECT MIN(quantity) FROM items")
    int getMinItemQuantity();

    @Query("SELECT * FROM (SELECT * FROM items WHERE id > :itemId ORDER BY id limit 1) UNION ALL SELECT * FROM (SELECT * FROM items WHERE id < :itemId ORDER BY id desc limit 1)")
    int[] getBothNearestIds(int itemId);

    @Query("SELECT tags FROM items")
    List<String> getAllTags();

    @Insert
    void insertAll(Item... items);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);
}
