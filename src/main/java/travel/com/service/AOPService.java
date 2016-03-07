package travel.com.service;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import travel.com.controller.LoginController;

@SuppressWarnings(
{ "unused", "unchecked" })
public class AOPService
{

	private static final Logger logger = Logger.getLogger(AOPService.class
			.getName());

	final long MAX_TIME = 700; // ms
	boolean ALL_TRACE = true;

	public Object GetExceTime(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable
	{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(proceedingJoinPoint.toShortString());
		boolean isExceptionThrown = false;
		try
		{
			// execute the profiled method
			return proceedingJoinPoint.proceed();
		} catch (RuntimeException e)
		{
			isExceptionThrown = true;
			throw e;
		} finally
		{
			stopWatch.stop();
			TaskInfo taskInfo = stopWatch.getLastTaskInfo();
			// Log the method's profiling result
			String AOPServiceMessage =
					taskInfo.getTaskName() + ": " + taskInfo.getTimeMillis()
							+ " ms"
							+ (isExceptionThrown ? " (thrown Exception)" : "");
			if (MAX_TIME <= taskInfo.getTimeMillis() || ALL_TRACE)
			{
				logger.info("AOPService :" + AOPServiceMessage);
			}

		}
	}
}
