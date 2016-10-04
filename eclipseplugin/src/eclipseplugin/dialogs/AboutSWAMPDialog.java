package eclipseplugin.dialogs;

import java.net.URL;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

public class AboutSWAMPDialog extends TitleAreaDialog {
	
	private static String ABOUT_SWAMP_TITLE = "About SWAMP";
	private static String ABOUT_SWAMP_NAME = "SWAMP Eclipse Plugin\n";
	//private static String ABOUT_SWAMP_RELEASE_DATE = // Get release date
	//private static String ABOUT_SWAMP_RELEASE_VERSION = // Get release version
	//private static String ABOUT_SWAMP_LINK = "";
	//private static String ABOUT_ECLIPSE_PLUGIN_LINK = "";
	//private static String ABOUT_SWAMP_GITHUB_LINK = "";
	private static String ABOUT_SWAMP_LICENSE_LINK = "Please visit <a href=\"https://www.apache.org/licenses/LICENSE-2.0\">Apache License</a> for licensing information.";
	private static String ABOUT_SWAMP_SUPPORT_INFO = "Please visit <a href=\"https://continuousassurance.org/support/\"> Continuous Assurance Support</a> for technical support.\n";
	
	public AboutSWAMPDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);	
		Composite container = new Composite(area, SWT.NONE);
		
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		
		this.setTitle(ABOUT_SWAMP_TITLE);
		
		String relDate = getFormattedReleaseDate();
		String releaseVersion = getReleaseVersion();
		DialogUtil.initializeLabelWidget(ABOUT_SWAMP_NAME + "\n" + "Release Date: " + relDate + "\n" + "Release version: " + releaseVersion, SWT.NONE, container);
		
		Link licenseLink = new Link(container, SWT.NONE);
		licenseLink.setText(ABOUT_SWAMP_LICENSE_LINK);
		licenseLink.addSelectionListener(new LinkSelectionAdapter());
		
		Link supportLink = new Link(container, SWT.NONE);
		supportLink.setText(ABOUT_SWAMP_SUPPORT_INFO);
		supportLink.addSelectionListener(new LinkSelectionAdapter());
		
		return area;
	}
	
	private String getFormattedReleaseDate() {
		return "10/04/2016";
	}
	
	private String getReleaseVersion() {
		//return org.eclipse.core.runtime.Platform.getBundle("swamp_eclipse_plugin").getHeaders().get("Bundle-Version");
		return "0.8.0";
	}
	
	public class LinkSelectionAdapter extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			try {
				PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
