import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

public class FoodGroupVisualizer {

    private JFrame frame;
    private JButton btnVisualizeAverageMeal;
    private JButton btnVisualizeCFG;
    private FoodGroupChartFactory chartFactory;

    public FoodGroupVisualizer(FoodGroupChartFactory chartFactory) {
        this.chartFactory = chartFactory;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Food Group Visualization");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        btnVisualizeAverageMeal = new JButton("Visualize Average Meal");
        frame.getContentPane().add(btnVisualizeAverageMeal);

        btnVisualizeCFG = new JButton("Visualize CFG");
        frame.getContentPane().add(btnVisualizeCFG);

        btnVisualizeAverageMeal.addActionListener(e -> visualizeChart("average meal"));
        btnVisualizeCFG.addActionListener(e -> visualizeChart("CFG"));
    }

    private void visualizeChart(String chartType) {
        JFreeChart chart = chartFactory.generateChart(chartType);
        displayChart(chart);
    }

    private void displayChart(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame chartFrame = new JFrame("Chart Visualization");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        chartFrame.setSize(800, 600);
        chartFrame.setVisible(true);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        FoodGroupChartFactory chartFactory = new FoodGroupChartFactory();
        FoodGroupVisualizer visualizer = new FoodGroupVisualizer(chartFactory);
        visualizer.setVisible(true);
    }
}
