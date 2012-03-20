package org.jboss.as.quickstarts.tasks.domain;

import java.util.List;

import javax.ejb.Local;

/**
 * Basic operations for manipulation with tasks
 * 
 * @author Lukas Fryc
 * 
 */
@Local
public interface TaskDao {

    void createTask(User user, Task task);

    List<Task> getAll(User user);

    List<Task> getRange(User user, int offset, int count);

    List<Task> getForTitle(User user, String title);

    void deleteTask(Task task);
}
