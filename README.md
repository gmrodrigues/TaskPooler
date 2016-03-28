# TaskPooler
Put threads in a pool and execute a given number of tasks at same time

```
        TaskPool taskPool = new TaskPool(2); //Create a task pool with capacity or running 2 tasks at same time
        try {
            taskPool.runTask(task1); // run task1 runnable
            taskPool.runTask(task2); // run task2 runnable
            taskPool.runTask(task3); // run task3 runnable
            taskPool.runTask(task4); // run task4 runnable
            taskPool.waitAllTaskToFinish(); // wait until all tasks finish
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        
```
