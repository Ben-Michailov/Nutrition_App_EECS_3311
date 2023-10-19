package nutrifit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WeightLossGui {

    private JFrame frame;
    private JTextField targetDateTextField;
    private JLabel resultLabel;
    private WeightLossCalculator calculator;

    public WeightLossGui(WeightLossCalculator calculator) {
        this.calculator = calculator;
        initialize();
    }

    
    private void initialize() {
        frame = new JFrame("Weight Loss Calculator");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnSubmit = new JButton("Calculate Weight Loss");
        btnSubmit.setBounds(100, 90, 200, 30);
        frame.getContentPane().add(btnSubmit);

        targetDateTextField = new JTextField();
        targetDateTextField.setBounds(200, 50, 150, 20);
        frame.getContentPane().add(targetDateTextField);
        targetDateTextField.setColumns(10);

        JLabel lblTargetDate = new JLabel("Target Date:");
        lblTargetDate.setBounds(50, 50, 150, 20);
        frame.getContentPane().add(lblTargetDate);


        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 130, 300, 20);
        frame.getContentPane().add(resultLabel);

        btnSubmit.addActionListener(e -> calculateWeightLoss());
    }

    private void calculateWeightLoss() {
        String targetDateStr = targetDateTextField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date parsedDate = dateFormat.parse(targetDateStr);
            Date targetDate = new Date(parsedDate.getTime());
            double projectedWeight = calculator.calculateWeightLoss(targetDate);
            resultLabel.setText("Projected weight on " + dateFormat.format(targetDate) + ": " + projectedWeight + " kg");
        } catch (ParseException e) {
            resultLabel.setText("Invalid date format. Please use yyyy-MM-dd.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {

        User user = new User();
        WeightLossCalculator calculator = new WeightLossCalculator(user);
        WeightLossGui gui = new WeightLossGui(calculator);

        gui.setVisible(true);

    }
}
