package pe.gob.sblm.sgi.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;




import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


public class SgiJob extends QuartzJobBean {

    private SgiTask sgiTask;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
			this.sgiTask.excecuteTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public SgiTask getSgiTask() {
		return sgiTask;
	}

	public void setSgiTask(SgiTask sgiTask) {
		this.sgiTask = sgiTask;
	}


}
