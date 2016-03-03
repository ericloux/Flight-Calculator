package e6b;

import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class E6B {
	
	DecimalFormat dc = new DecimalFormat("#.00");

	E6B() {
		
			// Create and initialize frame
		JFrame frame = new JFrame("Flight Calculator");
		frame.setLayout(new FlowLayout());
		frame.setSize(830, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			// Create and initialize frames
		JPanel conversionPanel = new JPanel();
		conversionPanel.setPreferredSize(new Dimension(390, 572));
		conversionPanel.setOpaque(true);
		conversionPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));

		JPanel computationPanel = new JPanel();
		computationPanel.setPreferredSize(new Dimension(410, 560));
		computationPanel.setOpaque(true);
		computationPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));
		
			// Create groups of components through member function
		JPanel milesPanel = conversionPair("Statute Miles:", "Nautical Miles:", .868976f);
		JPanel fuelWeightPanel = conversionPair("Fuel (gal):", "Weight (lbs):", 6);
		JPanel oilWeightPanel = conversionPair("Oil (qt):", "Weight (lbs):", (15/8));
		JPanel feetPanel = conversionPair("Feet:", "Meters:", .3048f);
		JPanel poundPanel = conversionPair("Pounds:", "Kilograms:", .453592f);
		
		JPanel timePanel = conversionTriple("Hours:", "Minutes", "Seconds:", 60f, 3600f);
		JPanel mphPanel = conversionTriple("MPH:", "Knots:", "Mach:", .868976f, .00130332f);
		JPanel gallonPanel = conversionTriple("US Gallons:", "Liters:", "Imperial Gallons:", 3.78541f, .832674f);
		
		JPanel groundspeedPanel = multiplyTriple("Distance:", "Groundspeed:", "Time:");
		JPanel fuelConsumptionPanel = multiplyTriple("Fuel Used", "Use Rate:", "Time:");
		
			// Create components for crosswind panel
		JPanel crosswindPanel = new JPanel();
		crosswindPanel.setLayout(new GridLayout(2, 5, 8, 8));
		crosswindPanel.setOpaque(true);
		crosswindPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));
		
		JLabel windspeedLabel = new JLabel("Windspeed:");
		JLabel windDirectionLabel = new JLabel("Rel. Wind:");
		JLabel headwindLabel = new JLabel("Headwind:");
		JLabel crosswindLabel = new JLabel("Crosswind:");
		
		JTextField windspeedField = new JTextField(4);
		JTextField windDirectionField = new JTextField(4);
		JTextField headwindField = new JTextField(4);
		JTextField crosswindField = new JTextField(4);

		headwindField.setEditable(false);
		crosswindField.setEditable(false);
		
		JButton crosswindButton = new JButton("Calc.");
		
		crosswindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double windspeed = Double.parseDouble(windspeedField.getText());
				double windDirection = Double.parseDouble(windDirectionField.getText());
				double crosswind = windspeed * Math.sin(Math.toRadians(windDirection));
				double headwind = windspeed * Math.cos(Math.toRadians(windDirection));

				headwindField.setText(dc.format(headwind));
				crosswindField.setText(dc.format(crosswind));
			}
		});

		crosswindPanel.add(windspeedLabel);
		crosswindPanel.add(windspeedField);
		crosswindPanel.add(windDirectionLabel);
		crosswindPanel.add(windDirectionField);
		crosswindPanel.add(crosswindButton);
		crosswindPanel.add(headwindLabel);
		crosswindPanel.add(headwindField);
		crosswindPanel.add(crosswindLabel);
		crosswindPanel.add(crosswindField);
		
			// Create components for density altitude
		JPanel densityPanel = new JPanel();
		densityPanel.setLayout(new GridLayout(6, 2, 8, 8));
		densityPanel.setOpaque(true);
		densityPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));
		
		JLabel pressureAltLabel = new JLabel("Pr Alt (Ft):");
		JLabel oatLabel = new JLabel("OAT (C):");
		JLabel densityAltLabel = new JLabel("Dn Alt (Ft):");
		JLabel iasLabel = new JLabel("KIAS:");
		JLabel tasLabel = new JLabel("KTAS:");
		JTextField pressureAltField = new JTextField(4);
		JTextField oatField = new JTextField(4);
		JTextField densityAltField = new JTextField(4);
		JTextField iasField = new JTextField(4);
		JTextField tasField = new JTextField(4);
		JButton densityAltButton = new JButton("Calculate");
		
		densityAltButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double isaTemperature = 15;
				double pressureAltitude = Double.parseDouble(pressureAltField.getText());
				isaTemperature -= (pressureAltitude / 1000) * 2;
				double oatTemperature = Double.parseDouble(oatField.getText());
				double densityAltitude = (oatTemperature - isaTemperature) * 120 + pressureAltitude;
				densityAltField.setText(dc.format(densityAltitude));
				
				double indicatedAirspeed = Double.parseDouble(iasField.getText());
				double trueAirspeed = indicatedAirspeed * (1 + (pressureAltitude * 2 / 100000));
				tasField.setText(dc.format(trueAirspeed));
			}
		});
		
		densityAltField.setEditable(false);
		tasField.setEditable(false);

		densityPanel.add(pressureAltLabel);
		densityPanel.add(pressureAltField);
		densityPanel.add(oatLabel);
		densityPanel.add(oatField);
		densityPanel.add(densityAltLabel);
		densityPanel.add(densityAltField);
		densityPanel.add(iasLabel);
		densityPanel.add(iasField);
		densityPanel.add(tasLabel);
		densityPanel.add(tasField);
		densityPanel.add(new JLabel(""));
		densityPanel.add(densityAltButton);
		
		
		JPanel windPanel = new JPanel();
		windPanel.setLayout(new GridLayout(8, 2, 8, 8));
		windPanel.setOpaque(true);
		windPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));
		
		JLabel windDirLabel = new JLabel("Mag Wind Dir:");
		JTextField windDirField = new JTextField(4);
		JLabel windSpeedLabel = new JLabel("Wind Speed (K):");
		JTextField windSpeedField = new JTextField(4);
		JLabel magneticCourseLabel = new JLabel("Mag Course:");
		JTextField magneticCourseField = new JTextField(4);
		JLabel trueAirspeedLabel = new JLabel("KTAS:");
		JTextField trueAirspeedField = new JTextField(4);
		JLabel wcaLabel = new JLabel("WCA:");
		JTextField wcaField = new JTextField(4);
		wcaField.setEditable(false);
		JLabel magneticHeadingLabel = new JLabel("Mag Heading:");
		JTextField magneticHeadingField = new JTextField(4);
		magneticHeadingField.setEditable(false);
		JLabel groundspeedLabel = new JLabel("Groundspeed (K):");
		JTextField groundspeedField = new JTextField(4);
		groundspeedField.setEditable(false);
		JButton windButton = new JButton("Calculate");
		
		windButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double windDirection = Double.parseDouble(windDirField.getText());
				double windSpeed = Double.parseDouble(windSpeedField.getText());
				double magneticCourse = Double.parseDouble(magneticCourseField.getText());
				double trueAirspeed = Double.parseDouble(trueAirspeedField.getText());
				
				double relativeWind = (windDirection - magneticCourse + 360) % 360;
				double relativeHeadwind = windSpeed * Math.cos(Math.toRadians(relativeWind));
				double relativeCrosswind = windSpeed * Math.sin(Math.toRadians(relativeWind));
				
					// forward component of airplane speed
				double forwardSpeed = Math.sqrt(Math.pow(trueAirspeed,2)-Math.pow(relativeCrosswind,2));

				double windCorrectionAngle = 90 - Math.toDegrees(Math.acos(relativeCrosswind/trueAirspeed));
				double magneticHeading = magneticCourse + windCorrectionAngle;
				double groundspeed = forwardSpeed - relativeHeadwind;
				
				wcaField.setText(dc.format(windCorrectionAngle));
				magneticHeadingField.setText(dc.format(magneticHeading));
				groundspeedField.setText(dc.format(groundspeed));
			}
		});

		windPanel.add(windDirLabel);
		windPanel.add(windDirField);
		windPanel.add(windSpeedLabel);
		windPanel.add(windSpeedField);
		windPanel.add(magneticCourseLabel);
		windPanel.add(magneticCourseField);
		windPanel.add(trueAirspeedLabel);
		windPanel.add(trueAirspeedField);
		windPanel.add(wcaLabel);
		windPanel.add(wcaField);
		windPanel.add(magneticHeadingLabel);
		windPanel.add(magneticHeadingField);
		windPanel.add(groundspeedLabel);
		windPanel.add(groundspeedField);
		windPanel.add(new JLabel(""));
		windPanel.add(windButton);
		
			// Create components for temperature panel
		JPanel temperaturePanel = new JPanel();
		JLabel labelCelsius = new JLabel("Celsius:");
		JLabel labelFarenheit = new JLabel("Farenheit:");
		JTextField textCelsius = new JTextField(3);
		JTextField textFarenheit = new JTextField(3);
		JButton buttonToCelsius = new JButton("<-");
		JButton buttonToFarenheit = new JButton("->");
		
			// Add enter key listeners to text boxes
		textCelsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double farenheit = Double.parseDouble(textCelsius.getText());
				farenheit *= 9;
				farenheit /= 5;
				farenheit += 32;
				textFarenheit.setText(dc.format(farenheit));
			}
		});

		textFarenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double celsius = Double.parseDouble(textFarenheit.getText());
				celsius -= 32;
				celsius *= 5;
				celsius /= 9;
				textCelsius.setText(dc.format(celsius));
			}
		});
		
			// Add action listeners to buttons
		buttonToFarenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double farenheit = Double.parseDouble(textCelsius.getText());
				farenheit *= 9;
				farenheit /= 5;
				farenheit += 32;
				textFarenheit.setText(dc.format(farenheit));
			}
		});

		buttonToCelsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double celsius = Double.parseDouble(textFarenheit.getText());
				celsius -= 32;
				celsius *= 5;
				celsius /= 9;
				textCelsius.setText(dc.format(celsius));
			}
		});
		
			// Add components to temperature panel
		temperaturePanel.add(labelCelsius);
		temperaturePanel.add(textCelsius);
		temperaturePanel.add(buttonToFarenheit);
		temperaturePanel.add(buttonToCelsius);
		temperaturePanel.add(labelFarenheit);
		temperaturePanel.add(textFarenheit);
		
		conversionPanel.add(temperaturePanel);
		conversionPanel.add(milesPanel);
		conversionPanel.add(fuelWeightPanel);
		conversionPanel.add(oilWeightPanel);
		conversionPanel.add(feetPanel);
		conversionPanel.add(poundPanel);
		conversionPanel.add(timePanel);
		conversionPanel.add(mphPanel);
		conversionPanel.add(gallonPanel);
		
		computationPanel.add(groundspeedPanel);
		computationPanel.add(fuelConsumptionPanel);
		computationPanel.add(crosswindPanel);
		computationPanel.add(densityPanel);
		computationPanel.add(windPanel);
		
		frame.add(conversionPanel);
		frame.add(computationPanel);
		frame.setVisible(true);
	}
	
	private JPanel multiplyTriple(String firstString,
			String secondString,
			String thirdString) {
		
		JPanel thisPanel = new JPanel();
		thisPanel.setLayout(new GridLayout(3, 3, 8, 8));
		thisPanel.setPreferredSize(new Dimension(300, 100));
		thisPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));
		
		JLabel firstLabel = new JLabel(firstString);
		JLabel secondLabel = new JLabel(secondString);
		JLabel thirdLabel = new JLabel(thirdString);

		JButton firstButton = new JButton("->");
		JButton secondButton = new JButton("->");
		JButton thirdButton = new JButton("->");
		
		JTextField firstText = new JTextField();
		JTextField secondText = new JTextField();
		JTextField thirdText = new JTextField();
		
		firstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.valueOf(secondText.getText()) *
						Double.valueOf(thirdText.getText());
				firstText.setText(dc.format(result));
			}
		});
		
		secondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.valueOf(firstText.getText()) /
						Double.valueOf(thirdText.getText());
				secondText.setText(dc.format(result));
			}
		});
		
		thirdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.valueOf(firstText.getText()) /
						Double.valueOf(secondText.getText());
				thirdText.setText(dc.format(result));
			}
		});

		thisPanel.add(firstLabel);
		thisPanel.add(firstButton);
		thisPanel.add(firstText);
		thisPanel.add(secondLabel);
		thisPanel.add(secondButton);
		thisPanel.add(secondText);
		thisPanel.add(thirdLabel);
		thisPanel.add(thirdButton);
		thisPanel.add(thirdText);
		
		return thisPanel;
	};
	
	private JPanel conversionTriple(String firstString,
			String secondString,
			String thirdString,
			double firstRatio,
			double secondRatio){
		
		JPanel thisPanel = new JPanel();
		
		thisPanel.setLayout(new GridLayout(3, 3, 8, 8));
		thisPanel.setPreferredSize(new Dimension(300, 100));
		thisPanel.setBorder(
				BorderFactory.createLineBorder(Color.GRAY));
		
		JLabel firstLabel = new JLabel(firstString);
		JLabel secondLabel = new JLabel(secondString);
		JLabel thirdLabel = new JLabel(thirdString);
		JTextField firstText = new JTextField(4);
		JTextField secondText = new JTextField(4);
		JTextField thirdText = new JTextField(4);
		JButton firstButton = new JButton("->");
		JButton secondButton = new JButton("->");
		JButton thirdButton = new JButton("->");

		firstText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double firstResult = Double.valueOf(firstText.getText());
				double secondResult = firstResult;
				
				firstResult *= firstRatio;
				secondResult *= secondRatio;
				secondText.setText(dc.format(firstResult));
				thirdText.setText(dc.format(secondResult));
			}
		});

		firstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double firstResult = Double.valueOf(firstText.getText());
				double secondResult = firstResult;
				
				firstResult *= firstRatio;
				secondResult *= secondRatio;
				secondText.setText(dc.format(firstResult));
				thirdText.setText(dc.format(secondResult));
			}
		});

		secondText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double firstResult = Double.valueOf(secondText.getText());
				
				firstResult /= firstRatio;
				double secondResult = firstResult;
				secondResult *= secondRatio;
				firstText.setText(dc.format(firstResult));
				thirdText.setText(dc.format(secondResult));
			}
		});

		secondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double firstResult = Double.valueOf(secondText.getText());
				
				firstResult /= firstRatio;
				double secondResult = firstResult;
				secondResult *= secondRatio;
				firstText.setText(dc.format(firstResult));
				thirdText.setText(dc.format(secondResult));
			}
		});

		thirdText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double thirdValue = Double.valueOf(thirdText.getText());
				double firstValue = thirdValue / secondRatio;
				double secondValue = firstValue * firstRatio;

				firstText.setText(dc.format(firstValue));
				secondText.setText(dc.format(secondValue));
			}
		});

		thirdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double thirdValue = Double.valueOf(thirdText.getText());
				double firstValue = thirdValue / secondRatio;
				double secondValue = firstValue * firstRatio;

				firstText.setText(dc.format(firstValue));
				secondText.setText(dc.format(secondValue));
			}
		});

		thisPanel.add(firstLabel);
		thisPanel.add(firstText);
		thisPanel.add(firstButton);
		thisPanel.add(secondLabel);
		thisPanel.add(secondText);
		thisPanel.add(secondButton);
		thisPanel.add(thirdLabel);
		thisPanel.add(thirdText);
		thisPanel.add(thirdButton);
		
		return thisPanel;
	}

	private JPanel conversionPair(String firstString,
			String secondString,
			double ratio) {
		
			// Create components
		JPanel thisPanel = new JPanel();
		JLabel firstLabel = new JLabel(firstString);
		JLabel secondLabel = new JLabel(secondString);
		JTextField firstText = new JTextField(4);
		JTextField secondText = new JTextField(4);
		JButton firstButton = new JButton("->");
		JButton secondButton = new JButton("<-");
		
			// Create action listeners
		firstText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.parseDouble(firstText.getText());
				result *= ratio;
				secondText.setText(dc.format(result));
			}
		});

		secondText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.parseDouble(secondText.getText());
				result /= ratio;
				firstText.setText(dc.format(result));
			}
		});
		
		firstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.parseDouble(firstText.getText());
				result *= ratio;
				secondText.setText(dc.format(result));
			}
		});
	
		secondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double result = Double.parseDouble(secondText.getText());
				result /= ratio;
				firstText.setText(dc.format(result));
			}
		});

			// add components
		thisPanel.add(firstLabel);
		thisPanel.add(firstText);
		thisPanel.add(firstButton);
		thisPanel.add(secondButton);
		thisPanel.add(secondLabel);
		thisPanel.add(secondText);
		
		return thisPanel;
	}
	
		// Creates E6B in the event dispatch thread
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new E6B();
			}
		});
	}

}
