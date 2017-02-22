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
import org.jfree.data.time.TimePeriodAnchor;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class Wykresy extends ApplicationFrame {
	public Wykresy( String title ) {
		super(title);
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		chartPanel.setMouseZoomable(true, true);
		setContentPane(chartPanel);
	}

	public Wykresy( String title, String title2, XYDataset dataset) {
		super(title);
		JFreeChart chart = createChart(dataset, title2);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		chartPanel.setMouseZoomable(true, true);
		setContentPane(chartPanel);
	}

	private JFreeChart createChart(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Legal & General Unit Trust Prices",
				"Date", "Price Per Unit",
				dataset,
				true,
				true,
				false
		);

		//PODPIS
		/*XYPlot plot = (XYPlot) chart.getPlot();

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

		ChartUtilities.applyCurrentTheme(chart);*/

		return chart;

	}

	private JFreeChart createChart(XYDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "Data", "Ocena", dataset, true, true, false);
		return chart;

	}


	private static XYDataset createDataset() {
		TimeSeries s1 = new TimeSeries("L&G European Index Trust");
		s1.add(new Day(1, 2, 2001), 181.8);
		s1.add(new Day(2, 2, 2001), 167.3);
		s1.add(new Day(3, 2, 2001), 153.8);
		s1.add(new Day(4, 2, 2001), 167.6);
		s1.add(new Day(5, 2, 2001), 158.8);
		s1.add(new Day(6, 2, 2001), 148.3);
		s1.add(new Day(7, 2, 2001), 153.9);
		s1.add(new Day(8, 2, 2001), 142.7);
		s1.add(new Day(9, 2, 2001), 123.2);
		s1.add(new Day(10, 2, 2001), 131.8);
		s1.add(new Day(11, 2, 2001), 139.6);
		s1.add(new Day(12, 2, 2001), 142.9);
		s1.add(new Day(13, 2, 2001), 138.7);
		s1.add(new Day(14, 2, 2001), 137.3);
		s1.add(new Day(15, 2, 2001), 143.9);
		s1.add(new Day(16, 2, 2001), 139.8);
		s1.add(new Day(17, 2, 2001), 137.0);
		s1.add(new Day(18, 2, 2001), 132.8);

		TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
		s2.add(new Day(1, 2, 2001), 129.6);
		s2.add(new Day(2, 2, 2001), 123.2);
		s2.add(new Day(3, 2, 2001), 117.2);
		s2.add(new Day(4, 2, 2001), 124.1);
		s2.add(new Day(5, 2, 2001), 122.6);
		s2.add(new Day(6, 2, 2001), 119.2);
		s2.add(new Day(7, 2, 2001), 116.5);
		s2.add(new Day(8, 2, 2001), 112.7);
		s2.add(new Day(9, 2, 2001), 101.5);
		s2.add(new Day(10, 2, 2001), 106.1);
		s2.add(new Day(11, 2, 2001), 110.3);
		s2.add(new Day(12, 2, 2001), 111.7);
		s2.add(new Day(13, 2, 2001), 111.0);
		s2.add(new Day(14, 2, 2001), 109.6);
		s2.add(new Day(15, 2, 2001), 113.2);
		s2.add(new Day(16, 2, 2001), 111.6);
		s2.add(new Day(17, 2, 2001), 108.8);
		s2.add(new Day(18, 2, 2001), 101.6);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		dataset.setXPosition(TimePeriodAnchor.MIDDLE);

		return dataset;
	}
}