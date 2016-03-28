package com.gmrodrigues.taskpooler.test;

import com.gmrodrigues.taskpooler.TaskPool;
import org.junit.Assert;
import org.junit.Test;

public class TaskPoolTest
{
    @Test
    public void testRunTask()
    {
        Runnable task1 = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Running Task 1");
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done Running Task 1");
            }
        };
        Runnable task2 = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Running Task 2");
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done Running Task 2");
            }
        };
        Runnable task3 = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Running Task 3");
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done Running Task 3");
            }
        };
        Runnable task4 = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Running Task 4");
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Done Running Task 4");
            }
        };

        TaskPool taskPool = new TaskPool(2);
        try {
            taskPool.runTask(task1);
            taskPool.runTask(task2);
            taskPool.runTask(task3);
            taskPool.runTask(task4);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(taskPool.getActiveTasksQuant());
        Assert.assertTrue("Should be running less than 2 threads", taskPool.getActiveTasksQuant() <= 2);
        try {
            taskPool.waitAllTaskToFinish();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
