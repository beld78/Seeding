package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainWindow {

	protected Shell shlSeedingTool;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private StyledText resultListHere;
	private Button btnFormat;
	private Label lblNewLabel;
	private Logic logic;
	private Button btnNewButton;
	private SecondWindow secondWindow;
	private Spinner groups;
	private Spinner personsInGroup;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		logic = new Logic();
		Display display = Display.getDefault();
		createContents();
		shlSeedingTool.open();
		shlSeedingTool.layout();

		while (!shlSeedingTool.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSeedingTool = new Shell();
		shlSeedingTool.setMinimumSize(new Point(371, 287));
		shlSeedingTool.setSize(525, 349);
		shlSeedingTool.setText("Seeding Tool");
		shlSeedingTool.setLayout(new GridLayout(4, false));

		StyledText enterListHere_1 = new StyledText(shlSeedingTool, SWT.BORDER);
		GridData gd_enterListHere_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 4);
		gd_enterListHere_1.heightHint = 220;
		enterListHere_1.setLayoutData(gd_enterListHere_1);
		enterListHere_1.setText("Enter list here");
		formToolkit.adapt(enterListHere_1);
		formToolkit.paintBordersFor(enterListHere_1);

		btnFormat = new Button(shlSeedingTool, SWT.NONE);
		btnFormat.setImage(SWTResourceManager.getImage(MainWindow.class, "/gui/rober.png"));
		btnFormat.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		btnFormat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				format(enterListHere_1);
			}

			private void format(StyledText enterListHere) {
				String textToConvert = enterListHere.getText();
				int amountOfGroups = groups.getSelection();
				int personsInGroupInt = personsInGroup.getSelection();

				String textToConvertArray[] = textToConvert.split("\\r?\\n");

				setLogic(textToConvertArray, personsInGroupInt, amountOfGroups);

				resultListHere.setText(logic.format());
			}

			private void setLogic(String[] textToConvertArray, int personsInGroup, int amountOfGroups) {
				logic.setTextToConvertArray(textToConvertArray);
				logic.setPersonsInGroup(personsInGroup);
				logic.setAmountOfGroups(amountOfGroups);
			}

		});
		formToolkit.adapt(btnFormat, true, true);
		btnFormat.setText("Click him!");

		resultListHere = new StyledText(shlSeedingTool, SWT.BORDER);
		resultListHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int end = resultListHere.getCharCount();
				resultListHere.setSelectionRange(0, end);
			}
		});
		GridData gd_resultListHere = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 4);
		gd_resultListHere.heightHint = 166;
		resultListHere.setLayoutData(gd_resultListHere);
		formToolkit.adapt(resultListHere);
		formToolkit.paintBordersFor(resultListHere);

		Label lblAmountOfGroups = new Label(shlSeedingTool, SWT.NONE);
		GridData gd_lblAmountOfGroups = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblAmountOfGroups.widthHint = 101;
		lblAmountOfGroups.setLayoutData(gd_lblAmountOfGroups);
		formToolkit.adapt(lblAmountOfGroups, true, true);
		lblAmountOfGroups.setText("Amount of Groups");

		groups = new Spinner(shlSeedingTool, SWT.BORDER);
		formToolkit.adapt(groups);
		formToolkit.paintBordersFor(groups);

		lblNewLabel = new Label(shlSeedingTool, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Persons / Group\r\n");

		personsInGroup = new Spinner(shlSeedingTool, SWT.BORDER);
		formToolkit.adapt(personsInGroup);
		formToolkit.paintBordersFor(personsInGroup);

		btnNewButton = new Button(shlSeedingTool, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				secondWindow = new SecondWindow(logic);
				secondWindow.start(logic);
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_btnNewButton.widthHint = 149;
		btnNewButton.setLayoutData(gd_btnNewButton);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("Adjust groups");

		Menu menu = new Menu(shlSeedingTool, SWT.BAR);
		shlSeedingTool.setMenuBar(menu);

		MenuItem mntmFormGroups = new MenuItem(menu, SWT.NONE);
		mntmFormGroups.setText("Form Groups");

		MenuItem mntmEditGroups = new MenuItem(menu, SWT.NONE);
		mntmEditGroups.setText("Edit Groups");

	}
}
