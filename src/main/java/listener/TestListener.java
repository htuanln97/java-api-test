package listener;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		log.info("===== START TEST: {} =====", result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("===== PASSED TEST: {} =====", result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.error("===== FAILED TEST: {} =====", result.getMethod().getMethodName(), result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.warn("===== SKIPPED TEST: {} =====", result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		log.info("===== SUITE START: {} =====", context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("===== SUITE FINISH: {} =====", context.getName());
	}
}
