package nutrifit;

import nutrifit.patterns.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class editPage extends JFrame implements ActionListener {

    editPageGUI c;

    /**
     * edit function
     *
     */

    public editPage(){
        c = new editPageGUI();
        c.getSub().addActionListener(this);
        c.getBack().addActionListener(this);
    }

    /**
     * button listener
     *
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == c.getSub()) {
            handleSubmitAction();
        } else if (e.getSource() == c.getBack()) {
            handleBackAction();
        }
    }
    /**
     * function for submit action when click button
     *
     */
    private void handleSubmitAction() {
        try {
            String sex = c.getMale().isSelected() ? "M" : "F";
            String dob = buildDateOfBirth();

            if (validateInputFields()) {
                User user = buildUser(sex, dob);
                UserProfile userProfile = new UserProfile();
                handleUserProfile(userProfile, user);
                updateUIAfterEdit(user);
            } else {
                c.getRes().setText("Input can not be null");
            }
        } catch (Exception ex) {
            handleException(ex);
        }
    }
    /**
     * builder for dob
     *
     */
    private String buildDateOfBirth() {
        return (String) c.getDate().getSelectedItem() + "/" + (String) c.getMonth().getSelectedItem() + "/" + (String) c.getYear().getSelectedItem();
    }
    /**
     * command method for user profile- insert,create new, edit
     *
     */
    private void handleUserProfile(UserProfile userProfile, User user) {
        try {
            if (userProfile.tableExists("user_profile")) {
                executeCommand(new EditCommand(userProfile, user));
            } else {
                executeCommand(new CreateNewCommand(userProfile));
                executeCommand(new InsertCommand(userProfile, user));
            }
        } catch (SQLException ex) {
            handleException(ex);
        }
    }
    /**
     * update to view in UI
     *
     */
    private void updateUIAfterEdit(User user) {
        c.getTout().setText(user.toString());
        c.getTout().setEditable(false);
        c.getRes().setText("Edit Successful");
    }
    /**
     * exception method
     *
     */
    private void handleException(Exception ex) {
        ex.printStackTrace();
    }
    /**
     * function for check if input is Null
     *
     */
    private boolean validateInputFields() {
        return !c.getTname().getText().isEmpty() && !c.getTage().getText().isEmpty()
                && !c.getTheight().getText().isEmpty() && !c.getTweight().getText().isEmpty();
    }
    /**
     * function for back action when click button
     *
     */
    private void handleBackAction() {
        c.setVisible(false);
        new welcomePage();
    }
    /**
     * command invoker
     *
     */
    private void executeCommand(Command command) {
        UserCommandInvoker invoker = new UserCommandInvoker();
        invoker.setCommand(command);
        invoker.executeCommand();
    }
    /**
     * build function for User
     *
     */
    private User buildUser(String sex, String dob) {
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setName(c.getTname().getText());
        userBuilder.setAge(c.getTage().getText());
        userBuilder.setDOB(dob);
        userBuilder.setHeight(c.getTheight().getText());
        userBuilder.setWeight(c.getTweight().getText());
        userBuilder.setSex(sex);

        return userBuilder.build();
    }


}
