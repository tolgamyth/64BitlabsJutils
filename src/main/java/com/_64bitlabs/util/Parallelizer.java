/*
 * Copyright (C) 2014-2016 Tolga Yilmaz
 * info@64bitlabs.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See LICENSE.txt for details.
 */

package com._64bitlabs.util;

import gnu.trove.set.hash.THashSet;

import java.util.*;

/**
 * Runs multiple jobs in parallel, n threads at a time, and waits
 * until all threads are complete before continuing.
 * <p>
 * The Parallelizer class is a Java thread utility that allows one to easily
 * convert their serial code to parallel code. The class would typically be used to
 * execute each iteration of a loop at once rather than one after another.
 * Good candidates for such an optimization would be when the order of execution does not
 * matter and each iteration does slow operations such as sleeping or making network connections.
 *
 * To use the Parallelizer, a developer would typically:
 * - Create a new Parallelizer before the loop.
 * - Put the contents of the loop inside an in-line Runnable instance.
 * - Copy all loop variables used inside the Runnable instance into finals
 * - Call join() after the loop to wait until the threads are done and it is safe to proceed/li
 * </p>
 *
 * <p>
 * Typically, Parallelizer would be used to run each of the items-
 * in a for loop at the same time.  For example the following for
 * loop:
 * <pre>
 * for (int i=0; i<10; i++){
 *    System.out.println("Hello World " + i);
 * }
 * System.out.println("done");
 * </pre>
 * To this:
 * <pre>
 * Parallelizer parallelizer = new Parallelizer();
 * for (int i=0; i<10; i++){
 *     final int j = i;
 *     parallelizer.run(
 *         new Runnable(){
 *             System.out.println("Hello World " + j);
 *         }
 *     );
 * }
 * parallelizer.join();
 * System.out.println("done");
 *
 * Example:
 * Parallelizer pll = new Parallelizer();
 * for (int i=0; i<10; i++){
 * 		final int j = i;
 * 		pll.run( new Runnable(){ System.out.println("Hello World " + j); } );
 * }
 * pll.join();
 *
 * @author Tolga Yilmaz - info@64bitlabs.com
 * @since 64bitlabsutils 1.05.00
 */
public class Parallelizer
{
	/**
	 * Constant that may be passed concurrentThreadLimit argument
	 * of the constructor indicating that no limit should be placed
	 * on the number of threads that are allowed to run concurrently.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public static final int INFINITE_THREAD_LIMIT = 0;

	/**
	 * The number of threads that are allowed to be run concurrently.
	 * (INFINITE_THREAD_LIMIT for no limit)
	 */
	private int concurrentThreadLimit = INFINITE_THREAD_LIMIT;

	/**
	 * Create a new Parallelizer with no limit on the number
	 * of threads that will be allowed to be run concurrently.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public Parallelizer(){
		this(INFINITE_THREAD_LIMIT);
	}

	/**
	 * Create a new Parallelizer with the specified limit on the number
	 * of threads that will be allowed to be run concurrently.
	 * <p>
	 * When the concurrent thread limit is reached and the parallelizer
	 * gets a new thread to run, the new thread will be queued until
	 * a thread finishes.
	 *
	 * @param concurrentThreadLimit number of threads that will be allowed
	 *     to run simultaneously or INFINITE_THREAD_LIMIT for no limit.
	 * @throws IllegalArgumentException if concurrentThreadLimit not a whole
	 *     number or INFINITE_THREAD_LIMIT
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public Parallelizer(int concurrentThreadLimit){
		if (concurrentThreadLimit < INFINITE_THREAD_LIMIT) throw new IllegalArgumentException("Bad concurrent thread limit: " + concurrentThreadLimit);
		this.concurrentThreadLimit = concurrentThreadLimit;
	}

	/**
	 * A Set of threads that are currently running.
	 * This set is also used as a lock to synchronize
	 * anything that touches running threads.
	 */
	private THashSet<Thread> runningThreads = new THashSet<>();

	/**
	 * A queue of jobs that have not yet been started.
	 */
	private LinkedList<Thread> toRunQueue = new LinkedList<>();

	/**
	 * Run the given job.  The given job is either run
	 * immediately or if the max number of concurrent jobs are already
	 * running, it is queued to be run when some job is finished.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @param job job which is to be run in parallel with other jobs.
	 * @throws Error if any thread that is already running has thrown an Error.
	 * @throws NullPointerException if job is null.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void run(Runnable job){
		run(null, job, null, 0);
	}

	/**
	 * Run the given job.  The given job is either run
	 * immediately or if the max number of concurrent jobs are already
	 * running, it is queued to be run when some job is finished.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @param job job which is to be run in parallel with other jobs.
	 * @param threadName name for the thread that will be created to run the job (null for auto generated thread name)
	 * @throws Error if any thread that is already running has thrown an Error.
	 * @throws NullPointerException if job is null.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void run(Runnable job, String threadName){
		run(null, job, threadName, 0);
	}

	/**
	 * Run the given job.  The given job is either run
	 * immediately or if the max number of concurrent jobs are already
	 * running, it is queued to be run when some job is finished.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @param threadGroup group in which this job should be run (null for default group).
	 * @param job job which is to be run in parallel with other jobs.
	 * @throws Error if any thread that is already running has thrown an Error.
	 * @throws NullPointerException if job is null.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void run(ThreadGroup threadGroup, Runnable job){
		run(threadGroup, job, null, 0);
	}

	/**
	 * Run the given job.  The given job is either run
	 * immediately or if the max number of concurrent jobs are already
	 * running, it is queued to be run when some job is finished.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @param threadGroup group in which this job should be run (null for default group).
	 * @param job job which is to be run in parallel with other jobs.
	 * @param threadName name for the thread that will be created to run the job (null for auto generated thread name)
	 * @throws Error if any thread that is already running has thrown an Error.
	 * @throws NullPointerException if job is null.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void run(ThreadGroup threadGroup, Runnable job, String threadName){
		run(threadGroup, job, threadName, 0);
	}

	/**
	 * Run the given job.  The given job is either run
	 * immediately or if the max number of concurrent jobs are already
	 * running, it is queued to be run when some job is finished.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @param threadGroup group in which this job should be run (null for default group).
	 * @param job job which is to be run in parallel with other jobs.
	 * @param threadName name for the thread that will be created to run the job (null for auto generated thread name)
	 * @param stackSize system dependent stack size suggestion for thread creation (0 for default stack size).
	 * @throws Error if any thread that is already running has thrown an Error.
	 * @throws NullPointerException if job is null.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void run(ThreadGroup threadGroup, final Runnable job, String threadName, long stackSize){
		throwFirstError();

		Runnable jobWrapper = new Runnable(){
			public void run(){
				try {
					job.run();
				} catch (RuntimeException runtimeException){
					// Put exceptions in the exception queue
					synchronized(runningThreads){
						exceptionList.add(runtimeException);
					}
				} catch (Error error){
					// Put errors in the error queue
					synchronized(runningThreads){
						errorList.add(error);
					}
				} finally {
					synchronized(runningThreads){
						// when done remove ourselves from the list
						// of running threads.
						runningThreads.remove(Thread.currentThread());
						// Notify the block method.
						runningThreads.notifyAll();
					}
					// If there are jobs queued up to be run, now would
					// be a good time to run them.
					startAJobIfNeeded();
				}
			}
		};

		// ensure the thread name is not null, and auto generate a name if it is
		threadName = getNextThreadName(threadName);

		// If we are already running the max number of jobs, queue this job up
		synchronized(runningThreads){
			toRunQueue.add(
				new Thread(
					threadGroup,
					jobWrapper,
					threadName,
					stackSize
				)
			);
		}

		// Now that the job is in the queue of jobs to run,
		// check the queue and see if the job should be started
		startAJobIfNeeded();
	}

	/**
	 * An number to assign to the next auto generated thread name
	 */
	private static int threadNameCount = 0;

	/**
	 * Ensure the given thread name is not null.  If not null, return it,
	 * if it is null, then then generate a name.
	 *
	 * @param threadName existing thread name to check
	 * @return the given thread name or a generated thread name if the specified name was null.
	 */
	private static String getNextThreadName(String threadName){
		if (threadName != null) return threadName;
		return "Parallelizer-"+(threadNameCount++);
	}

	/**
	 * A queue of exceptions that running threads have thrown.
	 */
	private LinkedList<RuntimeException> exceptionList = new LinkedList<>();

	/**
	 * Remove the first exception from the exception list and throw it.
	 *
	 * @throws RuntimeException if a running thread has thrown an exception not yet thrown by this method.
	 */
	private void throwFirstException(){
		synchronized(runningThreads){
			if (exceptionList.size() > 0){
				throw exceptionList.removeFirst();
			}
		}
	}

	/**
	 * A queue of exceptions that running threads have thrown.
	 */
	private LinkedList<Error> errorList = new LinkedList<>();

	/**
	 * Remove the first error from the error list and throw it.
	 *
	 * @throws Error if a running thread has thrown an error not yet thrown by this method.
	 */
	private void throwFirstError() throws Error {
		synchronized(runningThreads){
			if (errorList.size() > 0){
				throw errorList.removeFirst();
			}
		}
	}

	/**
	 * Remove a job from the toRunQueue, create a thread for it,
	 * start the thread, and put the job in the set of running jobs.
	 * But do all this only if there are jobs queued up to be run
	 * and we are not already running the max number of concurrent
	 * jobs at once.
	 */
	private void startAJobIfNeeded(){
		synchronized(runningThreads){
			// If we are already running the max number of jobs, just return
			if (concurrentThreadLimit != INFINITE_THREAD_LIMIT){
				if (runningThreads.size() >= concurrentThreadLimit) return;
			}

			// If there are no more job to run, return
			if (toRunQueue.size() == 0) return;

			// Get a job out of the queue
			Thread thread = toRunQueue.removeFirst();

			// Put the thread in the list of running threads
			runningThreads.add(thread);
			thread.start();
		}
	}

	/**
	 * Return true iff all jobs that have been requested to run
	 * in this Parallelizer have completed.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @return Whether all jobs are done or not.
	 * @throws Error if any of the running threads has thrown an Error.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public boolean done(){
		throwFirstError();
		synchronized(runningThreads){
			return (toRunQueue.size() + runningThreads.size()) == 0;
		}
	}

	/**
	 * All currently running threads will be interrupted.
	 * The threads interrupted threads may die, causing
	 * jobs that were queued but not yet started, to start.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @throws Error if any of the running threads has thrown an Error.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void interrupt(){
		throwFirstError();
		synchronized(runningThreads){
			for (Thread thread: runningThreads) {
				(thread).interrupt();
				throwFirstError();
			}
		}
	}

	/**
	 * Dump the stack of each running thread.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @throws Error if any of the running threads has thrown an Error.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void dumpStack(){
		throwFirstError();
		synchronized(runningThreads){
			for (Thread thread: runningThreads) {
				for (StackTraceElement stackTraceElement: thread.getStackTrace()){
					System.out.println(stackTraceElement.toString());
				}
				throwFirstError();
			}
		}
	}

	/**
	 * Gets a list of all running threads.  There may be jobs that
	 * are queued and do not yet have threads.  These job are not
	 * returned.
	 * <p>
	 * If this method throws an error, that
	 * error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the error.
	 *
	 * @throws Error if any of the running threads has thrown an Error.
	 * @return an array of all currently running threads.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public Thread[] getRunningThreads(){
		throwFirstError();
		synchronized(runningThreads){
			return runningThreads.toArray(new Thread[0]);
		}
	}

	/**
	 * Block until all the jobs in this Parallelizer have run
	 * and then return.
	 * <p>
	 * If this method throws an exception or an error, that
	 * exception or error may be handled and this method
	 * may be called again as it will not re-throw the same
	 * instance of the exception or error.
	 *
	 * @throws InterruptedException if interrupted while waiting.
	 * @throws RuntimeException any running thread throws or has thrown a runtime exception.
	 * @throws Error if any of the running threads throws or has thrown an Error.
	 *
	 * @since 64bitlabsutils 1.05.00
	 */
	public void join() throws InterruptedException {
		while (!done()){
			synchronized(runningThreads){
				throwFirstException();
				runningThreads.wait();
				throwFirstError();
				throwFirstException();
			}
		}
	}
}
