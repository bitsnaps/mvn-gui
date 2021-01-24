package org.ar.mvn.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.ar.mvn.gui.constants.PanelSources;
import org.ar.mvn.gui.constants.Sources;
import org.ar.mvn.gui.panels.NavigationMenuPanel;
import org.ar.mvn.gui.utils.UpdateUI;

import com.apple.eawt.Application;

public class GeneralFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel top;
  private JPanel center;
  private JPanel bottom;

  public GeneralFrame() {
    setTitle("MVN GUI");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}
    //
    setIcon();
    //
    initialize();
    //
    addToCenter(PanelSources.DESKTOP);
  }

  private void setIcon() {
    // icon
    try {

      setIconImage(Sources.APPLICATION_ICON.getImage());
      // check OS name and show doc icon if it is MAC
      if (isMac()) {
        Application application = Application.getApplication();
        application.setDockIconImage(Sources.APPLICATION_ICON.getImage());
      }
    } catch (Exception e) {
      // TODO logger
    }
  }

  private static String getOsName() {
    return System.getProperty("os.name").toLowerCase();
  }
  
  public static boolean isMac() {
	  return getOsName().contains("mac");
  }

  public static boolean isWindows() {
	  return getOsName().contains("win");
  }
  
  private void initialize() {
    setLayout(new BorderLayout());
    //
    top = new JPanel();
    center = new JPanel();
    bottom = new JPanel();
    //
    top.setPreferredSize(Sources.TOP_PANEL_SIZE);
    top.setBackground(Color.DARK_GRAY);
    confgureTopPanel();
    //
    center.setBackground(Color.gray);
    center.setLayout(new BorderLayout());
    //
    bottom.setPreferredSize(Sources.BOTTOM_PANEL_SIZE);
    bottom.setBackground(Color.DARK_GRAY);
    configureBottomPanel();
    //
    add(top, BorderLayout.NORTH);
    add(center, BorderLayout.CENTER);
    add(bottom, BorderLayout.SOUTH);
  }

  private void confgureTopPanel() {
    top.setLayout(new FlowLayout(FlowLayout.CENTER));
    top.add(new NavigationMenuPanel());
  }

  private void configureBottomPanel() {
    bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel introLable = new JLabel(Sources.INTRO);
    introLable.setForeground(Color.WHITE);
    bottom.add(introLable);
  }

  public void addToCenter(JPanel panel) {
    center.removeAll();
    center.add(panel, BorderLayout.CENTER);
    UpdateUI.update(center);
  }

  public JPanel getTop() {
    return top;
  }

  public JPanel getCenter() {
    return center;
  }

  public JPanel getBottom() {
    return bottom;
  }

}
