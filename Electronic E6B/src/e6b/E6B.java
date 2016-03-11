package e6b;

import java.text.DecimalFormat;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class E6B {
	
	DecimalFormat dc = new DecimalFormat("#.00");

	E6B() {
			// Initialize hash maps for METAR lookup
		initializeHashMaps();
		
			// Create and initialize frame
		JFrame frame = new JFrame("Flight Calculator");
		frame.setLayout(new FlowLayout());
		frame.setSize(430, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			// Create and initialize frames
		JPanel conversionPanel = new JPanel();
		conversionPanel.setPreferredSize(new Dimension(390, 572));
		conversionPanel.setOpaque(true);
		conversionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JPanel computationPanel = new JPanel();
		computationPanel.setPreferredSize(new Dimension(410, 560));
		computationPanel.setOpaque(true);
		computationPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		JPanel metarPanel = new JPanel();
		metarPanel.setPreferredSize(new Dimension(400, 560));
		metarPanel.setOpaque(true);
		metarPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
			// Create groups of components through member function
		JPanel milesPanel = conversionPair("Statute Miles:", "Nautical Miles:", .868976f);
		JPanel fuelWeightPanel = conversionPair("Fuel (gal):", "Weight (lbs):", 6);
		JPanel oilWeightPanel = conversionPair("Oil (qt):", "Weight (lbs):", (15/8));
		JPanel feetPanel = conversionPair("Feet:", "Meters:", .3048f);
		JPanel poundPanel = conversionPair("Pounds:", "Kilograms:", .453592f);
		
		JPanel timePanel = conversionTriple("Hours:", "Minutes:", "Seconds:", 60f, 3600f);
		JPanel mphPanel = conversionTriple("MPH:", "Knots:", "Mach:", .868976f, .00130332f);
		JPanel gallonPanel = conversionTriple("US Gallons:", "Liters:", "Imperial Gallons:", 3.78541f, .832674f);
		
		JPanel groundspeedPanel = multiplyTriple("Distance:", "Groundspeed:", "Time:");
		JPanel fuelConsumptionPanel = multiplyTriple("Fuel Used:", "Use Rate:", "Time:");
		
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
		
		JLabel windDirLabel = new JLabel("True Wind Dir:");
		JTextField windDirField = new JTextField(4);
		JLabel windSpeedLabel = new JLabel("Wind Speed (K):");
		JTextField windSpeedField = new JTextField(4);
		JLabel trueCourseLabel = new JLabel("True Course:");
		JTextField trueCourseField = new JTextField(4);
		JLabel trueAirspeedLabel = new JLabel("KTAS:");
		JTextField trueAirspeedField = new JTextField(4);
		JLabel wcaLabel = new JLabel("WCA:");
		JTextField wcaField = new JTextField(4);
		wcaField.setEditable(false);
		JLabel trueHeadingLabel = new JLabel("True Heading:");
		JTextField trueHeadingField = new JTextField(4);
		trueHeadingField.setEditable(false);
		JLabel groundspeedLabel = new JLabel("Groundspeed (K):");
		JTextField groundspeedField = new JTextField(4);
		groundspeedField.setEditable(false);
		JButton windButton = new JButton("Calculate");

		windPanel.add(windDirLabel);
		windPanel.add(windDirField);
		windPanel.add(windSpeedLabel);
		windPanel.add(windSpeedField);
		windPanel.add(trueCourseLabel);
		windPanel.add(trueCourseField);
		windPanel.add(trueAirspeedLabel);
		windPanel.add(trueAirspeedField);
		windPanel.add(wcaLabel);
		windPanel.add(wcaField);
		windPanel.add(trueHeadingLabel);
		windPanel.add(trueHeadingField);
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
		
			// Add components to temperature panel
		temperaturePanel.add(labelCelsius);
		temperaturePanel.add(textCelsius);
		temperaturePanel.add(buttonToFarenheit);
		temperaturePanel.add(buttonToCelsius);
		temperaturePanel.add(labelFarenheit);
		temperaturePanel.add(textFarenheit);
		
			// Create components for metarPanel
		JTextField jtfMetarField = new JTextField(30);
		JButton jbtInterpretMetar = new JButton("Interpret");
		JTextArea jtaMetarArea = new JTextArea(20,30);
		
		jtaMetarArea.setEditable(false);
		jtaMetarArea.setLineWrap(true);
		jtaMetarArea.setWrapStyleWord(true);
		
			// Add components to conversionPanel
		conversionPanel.add(temperaturePanel);
		conversionPanel.add(milesPanel);
		conversionPanel.add(fuelWeightPanel);
		conversionPanel.add(oilWeightPanel);
		conversionPanel.add(feetPanel);
		conversionPanel.add(poundPanel);
		conversionPanel.add(timePanel);
		conversionPanel.add(mphPanel);
		conversionPanel.add(gallonPanel);
		
			// Add components to computationPanel
		computationPanel.add(groundspeedPanel);
		computationPanel.add(fuelConsumptionPanel);
		computationPanel.add(crosswindPanel);
		computationPanel.add(densityPanel);
		computationPanel.add(windPanel);
		
			// Add components to metarPanel
		metarPanel.add(new JLabel("Enter METAR:"));
		metarPanel.add(jtfMetarField);
		metarPanel.add(jbtInterpretMetar);
		metarPanel.add(jtaMetarArea);
		
			// Make a tabbed pane to hold the panels
		JTabbedPane jtfMainPane = new JTabbedPane();
		jtfMainPane.addTab("Conversions", conversionPanel);
		jtfMainPane.addTab("Computations", computationPanel);
		jtfMainPane.addTab("METARS", metarPanel);
		frame.add(jtfMainPane);
		
		frame.setVisible(true);	
		
			// Add action listeners to buttons
		
		jbtInterpretMetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				
					// String and string iteration variables
				String metar = jtfMetarField.getText();
				String interpretation = "Weather conditions at ";
				char thisword[] = new char[4];
				int index = 0;
				
				interpretation = "Weather conditions at ";
				metar.getChars(index, index + 4, thisword, 0);
				interpretation += getStation(new String(thisword));
				index += 5;
				
				interpretation += ":\nTime: ";
				thisword = new char[6];
				metar.getChars(index, index + 6, thisword, 0);
				interpretation += getTime(new String (thisword));
				index += 8;

				interpretation += "\nWind:\n";
				thisword = new char[10];
				metar.getChars(index, index + 10, thisword, 0);
				if (thisword[0] == 'A') {
					index += 5;
					metar.getChars(index, index + 10, thisword, 0);
				}
				interpretation += getWind(new String(thisword));
				
				index += 8;
				if (thisword[5] == 'G') index += 3;
				
				interpretation += "\nVisibility: ";
				thisword = new char[2];
				metar.getChars(index, index + 2, thisword, 0);
				interpretation += new String(thisword);
				interpretation += " statute miles\n";
				index += 5;
				

				thisword = new char[3];
				metar.getChars(index, index + 3, thisword, 0);
				
				
				if (isCover(new String(thisword)) == false)
				{
					interpretation += "\nWeather Phenomena:\n";
					do {
						
						interpretation += "\t";
						
						if (thisword[0] == '-') {
							interpretation += "Light ";
							index++;
							metar.getChars(index, index + 3, thisword, 0);
						}
						
						if (thisword[0] == '+') { 
							interpretation += "Heavy ";
							index++;
							metar.getChars(index, index + 3, thisword, 0);
						}
						
						switch (thisword[0]) {
						case 'B':
							if (thisword[1] == 'C') 
								interpretation += "Patches ";
							if (thisword[1] == 'L') 
								interpretation += "Blowing ";
							if (thisword[1] == 'R') 
								interpretation += "Mist ";
							break;
							
						case 'D':
							if (thisword[1] == 'R') 
								interpretation += "Low Drifting ";
							if (thisword[1] == 'Z') 
								interpretation += "Drizzle ";
							if (thisword[1] == 'U') 
								interpretation += "Widespread Dust ";
							break;
							
						case 'F':
							if (thisword[1] == 'C') 
								interpretation += "Funnel Cloud ";
							if (thisword[1] == 'G') 
								interpretation += "Fog ";
							if (thisword[1] == 'U') 
								interpretation += "Smoke ";
							if (thisword[1] == 'Z') 
								interpretation += "Freezing ";
							break;
							
						case 'G':
							if (thisword[1] == 'R') 
								interpretation += "Hail ";
							if (thisword[1] == 'S') 
								interpretation += "Small Hail/Snow Pellets ";
							break;
							
						case 'H':
							if (thisword[1] == 'Z') 
								interpretation += "Haze ";
							break; 
							
						case 'I':
							if (thisword[1] == 'C') 
								interpretation += "Ice Crystals ";
							break;
							
						case 'M':
							if (thisword[1] == 'I') 
								interpretation += "Shallow ";
							break;
							
						case 'P':
							if (thisword[1] == 'R') 
								interpretation += "Partial ";
							if (thisword[1] == 'L') 
								interpretation += "Ice Pellets ";
							if (thisword[1] == 'Y') 
								interpretation += "Spray ";
							if (thisword[1] == 'O') 
								interpretation += "Well-Developed Dust/Sand Whirls ";
							break;
							
						case 'R':
							if (thisword[1] == 'A') 
								interpretation += "Rain ";
							break;
							
						case 'S':
							if (thisword[1] == 'H') 
								interpretation += "Showers ";
							if (thisword[1] == 'N') 
								interpretation += "Snow ";
							if (thisword[1] == 'G') 
								interpretation += "Snow Grains ";
							if (thisword[1] == 'A') 
								interpretation += "Sand ";
							if (thisword[1] == 'Q') 
								interpretation += "Squalls ";
							if (thisword[1] == 'S') 
								interpretation += "Sandstorm ";
							break;
							
						case 'T':
							if (thisword[1] == 'S') 
								interpretation += "Thunderstorm";
							break;
							
						case 'U':
							if (thisword[1] == 'P') 
								interpretation += "Unknown Precipitation ";
							break;
							
						case 'V':
							if (thisword[1] == 'A') 
								interpretation += "Volcanic Ash ";
							if (thisword[1] == 'C') 
								interpretation += "Nearby ";
							break;
							
						default:
							break;
						
						}
						interpretation += "\n";
						index += 2;
						metar.getChars(index, index + 3, thisword, 0);
						
					} while (thisword[0] != ' ');
					index++;
				}
					
				interpretation += "Cloud Cover:\n";
				thisword = new char[6];
				metar.getChars(index, index + 6, thisword, 0);

				if (thisword[3] != ' ') {
					while (thisword[2] != '/') {	
						interpretation += "\tSky ";

						thisword = new char[3];
						metar.getChars(index, index + 3, thisword, 0);
						
						switch (new String(thisword)) {
						case "FEW":
							interpretation += "0-25";
							break;
							
						case "SCT":
							interpretation += "25-50";
							break;
							
						case "BKN":
							interpretation += "50-90";
							break;
							
						case "OVC":
							interpretation += "85-100";
							break;
						}
						
						interpretation += "% obstructed at ";
						metar.getChars(index + 3, index + 6, thisword, 0);
						
						if (thisword[0] == '0') {
							if (thisword[1] == '0') {
								interpretation += thisword[1];
							}
							interpretation += thisword[2];
						} else
							interpretation += new String(thisword);
						
						interpretation += ",000ft AGL\n";
						
						index += 7;
						thisword = new char[6];
						metar.getChars(index, index + 6, thisword, 0);
					}
				} else {
					interpretation += "\tSky Clear\n";
					index += 4;
				}

				thisword = new char[2];
				metar.getChars(index, index + 2, thisword, 0);
				interpretation += "Temperature: ";
				
				if (thisword[0] == 'M') {
					thisword = new char[2];
					index++;
					metar.getChars(index, index + 2, thisword, 0);	
					interpretation += "-";
				}
				
				interpretation += new String (thisword);
				index += 3;
				metar.getChars(index, index + 2, thisword, 0);	

				interpretation += " C\nDew Point: ";
				
				if (thisword[0] == 'M') {
					thisword = new char[2];
					index++;
					metar.getChars(index, index + 2, thisword, 0);	
					interpretation += "-";
				}
				
				interpretation += new String (thisword);
				index += 4;
				thisword = new char[2];
				metar.getChars(index, index + 2, thisword, 0);	
				
				interpretation += " C\nPressure: ";
				interpretation += new String (thisword);

				index += 2;
				metar.getChars(index, index + 2, thisword, 0);	
				interpretation += ".";
				interpretation += new String (thisword);
				interpretation += " in. Hg\n";
				
				// index += 3 will put index right before RMKs
				
				jtaMetarArea.setText(interpretation);
			}
		});
		
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
		
		windButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent le) {
				double windDirection = Double.parseDouble(windDirField.getText());
				double windSpeed = Double.parseDouble(windSpeedField.getText());
				double trueCourse = Double.parseDouble(trueCourseField.getText());
				double trueAirspeed = Double.parseDouble(trueAirspeedField.getText());
				
				double relativeWind = (windDirection - trueCourse + 360) % 360;
				double relativeHeadwind = windSpeed * Math.cos(Math.toRadians(relativeWind));
				double relativeCrosswind = windSpeed * Math.sin(Math.toRadians(relativeWind));
				
					// forward component of airplane speed
				double forwardSpeed = Math.sqrt(Math.pow(trueAirspeed,2)-Math.pow(relativeCrosswind,2));

				double windCorrectionAngle = 90 - Math.toDegrees(Math.acos(relativeCrosswind/trueAirspeed));
				double trueHeading = trueCourse + windCorrectionAngle;
				double groundspeed = forwardSpeed - relativeHeadwind;
				
				wcaField.setText(dc.format(windCorrectionAngle));
				trueHeadingField.setText(dc.format(trueHeading));
				groundspeedField.setText(dc.format(groundspeed));
			}
		});
		
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
	}
	
	HashMap<String, String> stationMap = new HashMap<String, String>();
	
	private void initializeHashMaps(){
			// Initialize station names
		stationMap.put("KLWC", "Lawrence Municipal Airport in Lawrence, KS");
		stationMap.put("KTOP", "Billard Airport in Topeka, KS");
		stationMap.put("KFOE", "Forbe's Field Airport in Topeka, KS");
		stationMap.put("KEMP", "Emporia Municipal Airport in Emporia, KS");
	};
	
	private String getStation(String station) {
		return stationMap.get(station);
	};
	
	private String getTime(String time) {
		String result = "";
		char[] digit = new char[2];
		time.getChars(0, 2, digit, 0);

		if (digit[0] != '0') result += digit[0];
		result += digit[1];
		
		if (digit[0] != 1) {
			switch (digit[1]) {
			case 1:
				result += "st";
				break;
			
			case 2:
				result += "nd";
				break;
				
			case 3:
				result += "rd";
				break;
				
			default:
				result += "th";
			}
		} else result += "th";
		
		result += " day of month at ";

		time.getChars(2, 4, digit, 0);
		
		result += digit[0];
		result += digit[1];
		result += ":";
		time.getChars(4, 6, digit, 0);
		result += digit[0];
		result += digit[1];
		
		result += " Zulu time";
		
		return result;
	};
	
	private String getWind(String wind){
		String result = "\tDirection: ";
		char[] windDirection = new char[3];
		wind.getChars(0, 3, windDirection, 0);
		
		if (new String(windDirection).equals("VRB"))
			result += "Variable";
		else
			result += new String(windDirection);

		result += "\n\tVelocity: ";
		char[] windVelocity = new char[2];
		wind.getChars(3, 5, windVelocity, 0);
		result += new String(windVelocity);
		result += " knots";
		
		char[] gusts = new char[2];
		wind.getChars(6, 8, gusts, 0);
		
		if (gusts[1] != ' ') {
			result += ", gusting to ";
			result += new String(gusts);
			result += " knots";
		}
		
		return result;
	};
	
	private boolean isCover(String id) {
		if (id.equals("BKN"))
			return true;
		if (id.equals("OVC"))
			return true;
		if (id.equals("FEW"))
			return true;
		if (id.equals("SCT"))
			return true;
		if (id.equals("SKY"))
			return true;
		if (id.equals("CLR"))
			return true;
		
		return false;
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

		thisPanel.add(firstLabel);
		thisPanel.add(firstButton);
		thisPanel.add(firstText);
		thisPanel.add(secondLabel);
		thisPanel.add(secondButton);
		thisPanel.add(secondText);
		thisPanel.add(thirdLabel);
		thisPanel.add(thirdButton);
		thisPanel.add(thirdText);
		
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
