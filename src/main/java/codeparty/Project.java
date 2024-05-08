package codeparty;

import java.util.ArrayList;

public class Project extends Profile
{
	public Project(String id, String name, String bio, String ownerID, String tagline, String repositoryLink) {
		super(id,name, bio);
		this.ownerID = ownerID;
		this.tagline = tagline;
		this.repositoryLink = repositoryLink;
		links.put(Person.class, new ArrayList<String>());
		links.put(Skill.class, new ArrayList<String>());
	}
	
	public Project() {
		super();
		name = "button APP";
		tagline = "default tagline!";
		repositoryLink = "google.com";
		// TODO Auto-generated constructor stub
	}
	
	
	String ownerID;
	String tagline;
	String repositoryLink;
	/**
	 * @return the owner
	 */
	public String getOwnerID() {
		return ownerID;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	/**
	 * @return the tagline
	 */
	public String getTagline() {
		return tagline;
	}
	/**
	 * @param tagline the tagline to set
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	/**
	 * @return the repositoryLink
	 */
	public String getRepositoryLink() {
		return repositoryLink;
	}
	/**
	 * @param repositoryLink the repositoryLink to set
	 */
	public void setRepositoryLink(String repositoryLink) {
		this.repositoryLink = repositoryLink;
	}
	
}
