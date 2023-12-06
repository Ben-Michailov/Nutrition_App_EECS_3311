package nutrifit;

import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import nutrifit.ReadWriteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class DataVisualizationPage extends JFrame implements ActionListener {
	private JButton back;
	private JButton sub;
	private JLabel text1;
	private JTextField startDay;
	private JTextField startMonth;
	private JTextField startYear;
	private JLabel text2;
	private JLabel from;
	private JLabel to;
	private JTextField endDay;
	private JTextField endMonth;
	private JTextField endYear;
	private JButton top5;
	private JButton top10;
	private JButton barGraphReccomendedNutrients;
	private JButton barGraphMilli;
	private JButton barGraphMicro;
	private JButton foodGroups;
	private JButton calories;

	private ChartPanel chartPanel;
	// private JLabel successOrFail;

	ReadWriteController dataStorage = ReadWriteController.getInstance();

	public DataVisualizationPage() throws IOException {
		setTitle("Data Visualization Page");
		setBounds(300, 90, 925, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		Container c = getContentPane();
		c.setLayout(null);
		setVisible(true);

		this.initializeLabels();
		this.initializeTextFields();
		this.initializeButtons();
		// chartPanel.setEnabled(false);
	}

	private void initializeLabels() {
		text1 = createLabel("Visualize Your Data From a Given Date to a Given Date", 20, 10, 1000, 20);
		text2 = createLabel("Enter Start Date and Then End Date (DD-MM-YYYY)", 15, 10, 1000, 150);
		from = createLabel("from", 12, 10, 1000, 175);
		to = createLabel("to", 12, 10, 1000, 225);
	}

	private JLabel createLabel(String text, int fontSize, int width, int height, int yLocation) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", Font.PLAIN, fontSize));
		label.setSize(width, height);
		label.setLocation(10, yLocation);
		getContentPane().add(label);
		return label;
	}

	private void initializeTextFields() {
		startDay = createTextField(15, 25, 20, 200);
		startMonth = createTextField(15, 25, 20, 200);
		startYear = createTextField(15, 50, 20, 200);
		endDay = createTextField(15, 25, 20, 250);
		endMonth = createTextField(15, 25, 20, 250);
		endYear = createTextField(15, 50, 20, 250);
	}

	private JTextField createTextField(int fontSize, int width, int height, int yLocation) {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, fontSize));
		textField.setSize(width, height);
		textField.setLocation(10, yLocation);
		getContentPane().add(textField);
		return textField;
	}

	private void initializeButtons() {
		back = createButton("back", 15, 100, 20, 380);
		top5 = createButton("Top 5", 15, 100, 20, 460);
		top10 = createButton("Top 10", 15, 100, 20, 460);
		foodGroups = createButton("Food Groups", 15, 210, 20, 500);
		calories = createButton("Calories Over Time", 15, 300, 20, 420);
		barGraphReccomendedNutrients = createButton("Show Recommended Nutrients (g)", 15, 300, 20, 540);
		barGraphMilli = createButton("Show Recommended Nutrients (mg)", 15, 300, 20, 580);
		barGraphMicro = createButton("Show Recommended Nutrients (mcg)", 15, 300, 20, 620);
	}

	private JButton createButton(String text, int fontSize, int width, int height, int yLocation) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, fontSize));
		button.setSize(width, height);
		button.setLocation(80, yLocation);
		button.addActionListener(this);
		getContentPane().add(button);
		return button;
	}


	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == back) {
			setVisible(false);
			try {
				new HomePage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == top5) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;

			try {
				chart = generateChart.generateChart(startDate, endDate, 5);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}

				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		} else if (e.getSource() == top10) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;


			try {
				chart = generateChart.generateChart(startDate, endDate, 10);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}
				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == barGraphReccomendedNutrients) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;


			try {
				chart = generateChart.generateBarChartReccomendedMacro(startDate, endDate);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}
				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == barGraphMilli) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;


			try {
				chart = generateChart.generateBarChartReccomendedMilli(startDate, endDate);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}
				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == barGraphMicro) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;


			try {
				chart = generateChart.generateBarChartReccomendedMicro(startDate, endDate);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}
				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == foodGroups) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;


			try {
				chart = generateChart.generateBarChartFoodGroups(startDate, endDate);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}
				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == calories) {


			int startDayInt = Integer.valueOf(startDay.getText());
			int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
			int startYearInt = Integer.valueOf(startYear.getText()) - 1900;

			int endDayInt = Integer.valueOf(endDay.getText());
			int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
			int endYearInt = Integer.valueOf(endYear.getText()) - 1900;

			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			Date endDate = new Date(endYearInt, endMonthInt, endDayInt);


			GenerateChart generateChart = new GenerateChart();
			JFreeChart chart;


			try {
				chart = generateChart.generateLineChartCalories(startDate, endDate);
				if (chartPanel != null) {
					chartPanel.setChart(chart);
				} else {
					chartPanel = new ChartPanel(chart);
					chartPanel.setSize(450, 500);
					chartPanel.setLocation(450, 150);
					add(chartPanel);
				}
				repaint();
				revalidate();
				setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
  /* public static void main(String[] args) throws IOException, InterruptedException, Exception {  
		DataVisualizationPage test = new DataVisualizationPage();
   } */
}


