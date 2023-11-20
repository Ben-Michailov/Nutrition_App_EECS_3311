package nutrifit.patterns;

import nutrifit.UserProfile;

public class CreateNewCommand implements Command{
	private UserProfile userProfile;

	@Override
	public void execute() {
		userProfile.createNewTable();
		
	}
	
}
