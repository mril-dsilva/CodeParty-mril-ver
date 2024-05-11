package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import main.Main;
import server.ServerHandler;
import codeparty.Company;
import codeparty.JobPosting;
import codeparty.Page;
import codeparty.Person;
import codeparty.Project;
import codeparty.Skill;
import views.CompanyController;
import views.CompanyEditController;
import views.HomeController;
import views.JobController;
import views.JobEditController;
import views.LinkListCell;
import views.LoginController;
import views.NavController;
import views.ProjectController;
import views.ProjectEditController;
import views.SkillController;
import views.SkillEditController;
import views.UserEditController;
import views.UserViewController;

public class ViewTransitionModel implements ViewTransitionModelInterface {
	
	BorderPane mainView;
	BorderPane navView;
	SessionModel model = new SessionModel();
	
	String currentUserID;
	
	private Map<String, Page> fakeData = new HashMap<>();
	
	public void setFakeData(Map<String, Page> fakeData) {
		this.fakeData = fakeData;
	} 
	
	public void setMainView(BorderPane mainView) {
		this.mainView = mainView;
	}
	
	public void setCurrentUserID(String ID) {
		this.currentUserID = ID;
	}
	
	public void showLogin() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/loginView.fxml"));
		
		try {
			Pane view = loader.load();
			mainView.setCenter(view);
			LoginController controller = loader.getController();
			LoginModel model = new LoginModel();
			controller.setModels(model, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showUser(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/userView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			UserViewController controller = loader.getController();
			controller.setModel(this);
			controller.populateUser(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showHome() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/homeView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			HomeController controller = loader.getController();
			controller.setModel(this);
			//controller.setModel(model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showCurrentUser() {
		this.showUser(currentUserID);
	}

	// If coming from the login screen, which does not have the nav buttons, we need to create
	// the nav view to place all subsequent views in
	@Override
	public void showCurrentUserFromLogin() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/navView.fxml"));
		
		try {
			navView = loader.load();
			mainView.setCenter(navView);
			NavController controller = loader.getController();
			controller.setModel(this);
			
			showCurrentUser();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Page getObject(String id) {
		return fakeData.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Page> ArrayList<T> getAllObjectsOfType(Class<T> type) {
		ArrayList<T> returnData = new ArrayList<T>();

		if (type == Person.class) {
			returnData = (ArrayList<T>) ServerHandler.retrieveAllPersons();
		} else if (type == Project.class) {
			returnData = (ArrayList<T>) ServerHandler.retrieveAllProjects();
		} else if (type == Skill.class) {
			returnData = (ArrayList<T>) ServerHandler.retrieveAllSkills();
		} else if (type == Company.class) {
			returnData = (ArrayList<T>) ServerHandler.retrieveAllCompanys();
		} else if (type == JobPosting.class) {
			returnData = (ArrayList<T>) ServerHandler.retrieveAllJobs();
		} 
		
//		for(Map.Entry<String, Page> entry : fakeData.entrySet()) {
//			@SuppressWarnings("unchecked")
//			T page = (T) entry.getValue();
//			
//			if(page.getClass() == type) {
//				returnData.add(page);
//			}
//		}
		
		return returnData;
	}

	private void showList(ListModel model) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/listView.fxml"));
	    try {
	    	ListView<LinkData> view = loader.load();
			navView.setCenter(view);
			ViewTransitionModel transitionModel = this;
	      
			view.setCellFactory(new Callback<ListView<LinkData>, ListCell<LinkData>>()
			  {

				@Override
				public ListCell<LinkData> call(ListView<LinkData> lv)
				{
					return new LinkListCell(transitionModel);
				}
			  });
			
			view.setItems(model.getItems());
			
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
	}

	@Override
	public void showCompany(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/companyView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			CompanyController controller = loader.getController();
			controller.setModel(this);
			controller.populateCompany(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showProject(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/projectView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			ProjectController controller = loader.getController();
			controller.setModel(this);
			controller.populateProject(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
	@Override
	public void showSkill(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/skillView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			SkillController controller = loader.getController();
			controller.setModel(this);
			controller.populateSkill(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showJobPosting(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/jobPostingView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			JobController controller = loader.getController();
			controller.setModel(this);
			controller.populateJob(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public <T extends Page> void showListOfAll(Class<T> type) {
		ArrayList<T> pages = this.getAllObjectsOfType(type);
    	
    	ListModel listModel = new ListModel();
    	for(T page : pages) {
    		listModel.addItem(new LinkData(page.getName(), page.getId(), type));
    		System.out.println(page.getId());
    	}
    	
    	this.showList(listModel);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Page> void showListOfLinks(Page page, Class<T> type) {
		ArrayList<String> links = page.getLinks().get(type);
		ArrayList<T> pages = new ArrayList<T>();
		
		for(String link : links) {
			try {
				if (type == Person.class) {
					System.out.println();
					Person object = ServerHandler.getPersonObject(link);
					pages.add((T) object);
					System.out.println(object);
				} else if (type == Project.class) {
					Project object = ServerHandler.getProjectObject(link);
					pages.add((T) object);
					System.out.println(object);
				} else if (type == Skill.class) {
					Skill object = ServerHandler.getSkillObject(link);
					pages.add((T) object);
				} else if (type == Company.class) {
					Company object = ServerHandler.getCompanyObject(link);
					pages.add((T) object);
				} else if (type == JobPosting.class) {
					JobPosting object = ServerHandler.getJobPostingObject(link);
					pages.add((T) object);
				}
				
			} catch (Exception E) {
		    			
		    }
		}
		
		ListModel listModel = new ListModel();
    	for(Page receivedPage : pages) {
        	listModel.addItem(new LinkData(receivedPage.getName(), receivedPage.getId(), type));
    	}
    	
    	this.showList(listModel);
		
	}

	public Person getCurrentUser() {
		return ServerHandler.getPersonObject(currentUserID);
	}

	@Override
	public ArrayList<Page> getLinksOf(String id, Class<? extends Page> linkType) {
		ArrayList<Page> links = new ArrayList<>();
		
		if (linkType == Person.class) {
			Person object = ServerHandler.getPersonObject(id);
			ArrayList<String> linkIDs = object.getLinks().get(linkType);
			for (String linkID : linkIDs) {
				links.add(ServerHandler.getPersonObject(linkID));
			}
		} else if (linkType == Project.class) {
			Project object = ServerHandler.getProjectObject(id);
			ArrayList<String> linkIDs = object.getLinks().get(linkType);
			for (String linkID : linkIDs) {
				links.add(ServerHandler.getProjectObject(linkID));
			}
		} else if (linkType == Skill.class) {
			Skill object = ServerHandler.getSkillObject(id);
			ArrayList<String> linkIDs = object.getLinks().get(linkType);
			for (String linkID : linkIDs) {
				links.add(ServerHandler.getSkillObject(linkID));
			}
		} else if (linkType == Company.class) {
			Company object = ServerHandler.getCompanyObject(id);
			ArrayList<String> linkIDs = object.getLinks().get(linkType);
			for (String linkID : linkIDs) {
				links.add(ServerHandler.getCompanyObject(linkID));
			}
		} else if (linkType == JobPosting.class) {
			JobPosting object = ServerHandler.getJobPostingObject(id);
			ArrayList<String> linkIDs = object.getLinks().get(linkType);
			for (String linkID : linkIDs) {
				links.add(ServerHandler.getJobPostingObject(linkID));
			}
		}
		
		
		return links;
	}

	@Override
	public void addLinkToCurrentUser(String idToAdd, Class<? extends Page> linkType) {
		getCurrentUser().addLink(linkType, idToAdd);
	}

	@Override
	public void showUserEdit(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/userEditView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			UserEditController controller = loader.getController();
			controller.setModel(this);
			controller.populateUser(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showCompanyEdit(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/companyEditView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			CompanyEditController controller = loader.getController();
			controller.setModel(this);
			controller.populateCompany(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void showProjectEdit(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/projectEditview.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			ProjectEditController controller = loader.getController();
			controller.setModel(this);
			controller.populateProject(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
	
	@Override
	public void showSkillEdit(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/skillEditview.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			SkillEditController controller = loader.getController();
			controller.setModel(this);
			controller.populateSkill(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void showJobPostingEdit(String id) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/jobPostingEditView.fxml"));
		
		try {
			Pane view = loader.load();
			navView.setCenter(view);
			JobEditController controller = loader.getController();
			controller.setModel(this);
			controller.populateJob(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean canEdit(Page page) {
		ArrayList<String> editors = page.getUsersCanEdit();
		
		for(String editor : editors) {
			if(editor.equals(currentUserID)) {
				return true;
			}
		}
		
		return false;
		
	}
}
