package nutrifit.patterns;

import nutrifit.UserProfile;

public class CreateNewCommand implements Command{
	private UserProfile userProfile;
	
	public CreateNewCommand(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	@Override
	public void execute() {
		userProfile.createNewTable();
		
	}

}
