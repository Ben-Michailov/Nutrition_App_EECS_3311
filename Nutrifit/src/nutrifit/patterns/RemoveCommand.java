package nutrifit.patterns;

import nutrifit.UserProfile;

public class RemoveCommand implements Command{
	private UserProfile userProfile;

	@Override
	public void execute() {
		userProfile.removeTable();
		
	}
}
