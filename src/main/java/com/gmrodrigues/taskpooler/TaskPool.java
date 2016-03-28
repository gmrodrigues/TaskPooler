package com.gmrodrigues.taskpooler;

import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

public class TaskPool
{
    PooledExecutor executor;
    int maxPoolSize;

    ActiveTasksCounter counter = new ActiveTasksCounter();

    public TaskPool(int poolSize)
    {
        executor = new PooledExecutor();
        executor.setMinimumPoolSize(0);
        setSlotQuant(poolSize);
    }

    public TaskPool()
    {
        this(8);
    }

    public int getSlotQuant()
    {
        return maxPoolSize;
    }

    public void setSlotQuant(int slots)
    {
        this.maxPoolSize = slots;
        executor.setMaximumPoolSize(slots);
    }

    public void runTask(Runnable task) throws InterruptedException
    {
        executor.execute(new Task(task));
    }

    public void shutdown() throws InterruptedException
    {
        executor.shutdownNow();
        executor.awaitTerminationAfterShutdown();
    }

    public int getPoolSize()
    {
        return executor.getPoolSize();
    }

    public int getActiveTasksQuant()
    {
        return counter.getNumberOfActiveTasks();
    }

    private class ActiveTasksCounter
    {
        private int activeTasks;
        private int remainingTasks;

        synchronized void taskEnqueued()
        {
            remainingTasks++;
        }

        synchronized void taskStarted()
        {
            activeTasks++;
        }

        synchronized void taskFinished()
        {
            activeTasks--;
            remainingTasks--;
        }

        synchronized int getNumberOfActiveTasks()
        {
            return activeTasks;
        }

        synchronized int getNumberOfRemainingTasks()
        {
            return remainingTasks;
        }
    }

    private class Task implements Runnable
    {
        Runnable task;

        public Task(Runnable task)
        {
            this.task = task;
            counter.taskEnqueued();
        }

        public void run()
        {
            counter.taskStarted();
            task.run();
            counter.taskFinished();
        }
    }

    public void waitAllTaskToFinish(int sleepMs) throws InterruptedException
    {
        while (counter.getNumberOfRemainingTasks() > 0) {
            Thread.sleep(sleepMs);
        }
    }

    public void waitAllTaskToFinish() throws InterruptedException
    {
        waitAllTaskToFinish(500);
    }
}
