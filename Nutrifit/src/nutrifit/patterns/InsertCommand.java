package nutrifit.patterns;

import nutrifit.UserProfile;

public class InsertCommand implements Command{
	private UserProfile userProfile;
    private User user;

    public InsertCommand(UserProfile userProfile, User user) {
        this.userProfile = userProfile;
        this.user = user;
    }

    @Override
    public void execute() {
        userProfile.insertTable(user);
    }
}
