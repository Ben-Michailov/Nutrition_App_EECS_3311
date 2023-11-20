package nutrifit.patterns;

import nutrifit.UserProfile;

public class EditCommand implements Command{
	private UserProfile userProfile;
    private User user;

    public EditCommand(UserProfile userProfile, User user) {
        this.userProfile = userProfile;
        this.user = user;
    }

    @Override
    public void execute() {
        userProfile.editTable(user);
    }
}
