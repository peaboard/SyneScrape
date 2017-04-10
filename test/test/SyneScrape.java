/**
 * @author Uros Krcadinac
 * Oct 8, 2009
 * @version 0.1
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.*;
import java.util.concurrent.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import synesketch.gui.EmpathyPanel;
import synesketch.emotion.EmotionalState;
import synesketch.emotion.Empathyscope;
import synesketch.app.Empathybox;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class SyneScrape {
	
	private static String fileName = "test/test/inputBaseline/website_scrape.txt";
	
	private static String outputFileName = "test/test/outputBaseline/resultsSurpriseBaseline.txt";

	private static List<EmotionalState> sents = new ArrayList<EmotionalState>();
	
	private static PrintWriter output;

	public static void main(String[] args) throws Exception {
		
//		String website = new String ("https://en.wikipedia.org/wiki/Plug-in_electric_vehicle");
		
		String website = new String ("https://en.wikipedia.org/wiki/Battle_of_the_Twin_Villages");
		
		String[] target = {"test/test/scrape_website.sh", website};

		
		ProcessBuilder pb = new ProcessBuilder(target);
		 Process p = pb.start();
		 BufferedReader errorreader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		 BufferedReader outputreader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 String shellerr_line = null;
		 String shellout_line = null;
		 while ((shellerr_line = errorreader.readLine()) != null & (shellout_line = outputreader.readLine()) != null)
		 {
		    //System.out.println(shellerr_line);
		    System.out.println(shellout_line);
		 }
		
//		ProcessBuilder pb = new ProcessBuilder("/home/dande/Software Snippets/Scrapy/Airbnb/bnbtutorial/scrape_website.sh", "https://en.wikipedia.org/wiki/Battle_of_the_Twin_Villages");
//		 Map<String, String> env = pb.environment();
//		 env.put("VAR1", "myValue");
//		 env.remove("OTHERVAR");
//		 env.put("VAR2", env.get("VAR1") + "suffix");
//		 pb.directory(new File("myDir"));
//		 Process p = pb.start();
		
//		Runtime rt = Runtime.getRuntime();
//		Process proc = rt.exec(target);
//		proc.waitFor();
//		StringBuffer shelloutput = new StringBuffer();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//		//TimeUnit.SECONDS.sleep(5);
//		String shell_line = "";                       
//        while ((shell_line = reader.readLine())!= null) {
//                shelloutput.append(shell_line + "\n");
//        }
//        System.out.println("### " + shelloutput);
		
		String line = "";
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		File outputFile = new File(outputFileName);
		output = new PrintWriter(new FileOutputStream(outputFile));
		Empathybox application = new Empathybox();
		
		application.getJFrame().setVisible(true);
		
		do {
			TimeUnit.MILLISECONDS.sleep(1000);
			line = in.readLine();
			if (line == null) break;
			if (line.contains("As much as I hated Otoya through the first half of the series, I love him to pieces now.")) {
				line = "As much as I hated Otoya through the first half of the series, I love him to pieces now.";
			}

			EmotionalState sentenceState = Empathyscope.getInstance().feel(line);
			
			writeSentenceState(sentenceState);
			computeAccuracy(sentenceState);
			
			application.fillJTextArea(line);

			sents.add(sentenceState);
		} while (line != null);
		in.close();
		
		System.out.println("total: " + totalSentenceCount);
		System.out.println("accurate: " + accurateSentenceCount);
		double acc = accurateSentenceCount/totalSentenceCount;
		System.out.println("ACCURACY: " + acc);
		//System.out.println(sents);
		
	
				
				

		
	}
	
	public static void writeSentenceState(EmotionalState arg) throws Exception {
		double h = arg.getHappinessWeight();
		double sd = arg.getSadnessWeight();
		double a = arg.getAngerWeight();
		double f = arg.getFearWeight();
		double d = arg.getDisgustWeight();
		double su = arg.getSurpriseWeight();
		
		//int v = arg.getValence();
		output.println(h + " " + sd + " " + a + " " + f + " " + d + " " + su);
		output.flush();
	}
	
	private static double totalSentenceCount = 0;
	private static double accurateSentenceCount = 0;

	public static void computeAccuracy(EmotionalState arg) throws Exception {
		double[] state = new double[6];
		state[0] = arg.getHappinessWeight();
		state[1] = arg.getSadnessWeight();
		state[2] = arg.getAngerWeight();
		state[3] = arg.getFearWeight();
		state[4] = arg.getDisgustWeight();
		state[5] = arg.getSurpriseWeight();
		
		totalSentenceCount++;
		if (state[5] == state[getMaxIndex(state)]) {
			accurateSentenceCount++;
		}
	}
	
	public static int getMaxIndex(double[] arg) {
		int value = 0;
		for (int i = 0; i < arg.length; i++) {
			if (arg[i] > arg[value]) {
				value = i;
			}
		}
		return value;
	}
	
}
