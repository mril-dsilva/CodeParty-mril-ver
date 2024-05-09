package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.ViewTransitionModel;
import codeparty.Company;
import codeparty.JobPosting;

public class JobController {

    @FXML
    private Button applyButton;

    @FXML
    private Button editButton;

    @FXML
    private Label jobCompanyLabel;

    @FXML
    private Label jobDescriptionLabel;

    @FXML
    private Label jobNameLabel;

    @FXML
    void onEditButtonClick(ActionEvent event) {
    	model.showJobPostingEdit(job.getId());
    }
    
    ViewTransitionModel model;
	JobPosting job;
	
	public void setModel(ViewTransitionModel model)
	{
		this.model=model;
	}
	
	public void populateJob(String id) {
		job = (JobPosting)model.getObject(id);
		
		if(job!=null)
		{
			jobNameLabel.textProperty().set(job.getName());
			
			jobDescriptionLabel.textProperty().set(job.getBody());
			
			jobCompanyLabel.textProperty().set(((Company)model.getObject(job.getCompanyID())).getName()); //SEEMS LIKE THERE NEEDS TO BE A FIX HERE TO WORK WITH REStseRVER
			
		}
		
		if(!model.canEdit(job)) {
			editButton.setVisible(false);
		}
		
	}

}

