package codeparty;

import java.util.ArrayList;

import codeparty.Person;
import codeparty.Skill;

public class Skill extends Profile
{
    String tagline;
    
    public Skill(String id, String name, String bio, String tagline) {
		super(id, name, bio);
		this.tagline = tagline;
		// TODO Auto-generated constructor stub
		links.put(Person.class, new ArrayList<String>());
		links.put(Skill.class, new ArrayList<String>());
	}

	public Skill() {
		super();
		name = "JAVA";
		tagline = "Skill tagline!";
		links.put(Person.class, new ArrayList<String>());
		links.put(Skill.class, new ArrayList<String>());
	}
	
	public Skill(String id) {
		super();
		// TODO Auto-generated constructor stub
		links.put(Person.class, new ArrayList<String>());
		links.put(Skill.class, new ArrayList<String>());
	}
	
    // Getter and setter for tagline
    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    
	
}
