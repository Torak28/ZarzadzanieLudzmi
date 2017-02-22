import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;

public class Wykresy extends JFrame {
	public Wykresy( String title, String title2, XYDataset dataset) {
		super(title);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JFreeChart chart = createChart(dataset, title2);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		chartPanel.setMouseZoomable(true, true);
		setContentPane(chartPanel);
	}

	private JFreeChart createChart(XYDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "Data", "Ocena", dataset, true, true, false);

		XYPlot plot = (XYPlot) chart.getPlot();

		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer renderer = plot.getRenderer();
		if (renderer instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer rr = (XYLineAndShapeRenderer) renderer;
			rr.setBaseShapesVisible(true);
			rr.setBaseShapesFilled(true);
			rr.setBaseItemLabelsVisible(true);
		}

		PeriodAxis domainAxis = new PeriodAxis("Date");
		domainAxis.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
		domainAxis.setAutoRangeTimePeriodClass(Day.class);
		PeriodAxisLabelInfo[] info = new PeriodAxisLabelInfo[3];
		info[0] = new PeriodAxisLabelInfo(Day.class, new SimpleDateFormat("d"));
		info[1] = new PeriodAxisLabelInfo(Month.class,
				new SimpleDateFormat("MMM"), new RectangleInsets(2, 2, 2, 2),
				new Font("SansSerif", Font.BOLD, 10), Color.blue, false,
				new BasicStroke(0.0f), Color.lightGray);
		info[2] = new PeriodAxisLabelInfo(Year.class,
				new SimpleDateFormat("yyyy"));
		domainAxis.setLabelInfo(info);
		plot.setDomainAxis(domainAxis);

		ChartUtilities.applyCurrentTheme(chart);

		return chart;

	}
}