package com.jbproductions.liszt;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * DAO to provide query functionality for Task Entities.
 */
@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasks WHERE id = :id")
    Task getTaskById(long id);

    @Query("DELETE FROM tasks WHERE id = :id")
    void deleteTaskById(long id);

    @Query("SELECT * FROM tasks WHERE complete=0 UNION ALL SELECT * from tasks WHERE complete=1")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT id, name, complete FROM tasks WHERE complete=0")
    LiveData<List<Task>> getOpenTasks();

    @Query("SELECT id, name, complete FROM tasks WHERE complete=1")
    LiveData<List<Task>> getCompleteTasks();
}
