package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class TestWindow {

	protected Shell shlSwtApplication;
	private Table table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestWindow window = new TestWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSwtApplication.open();
		shlSwtApplication.layout();
		while (!shlSwtApplication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSwtApplication = new Shell();
		shlSwtApplication.setSize(450, 300);
		shlSwtApplication.setText("SWT Application");
		shlSwtApplication.setLayout(new GridLayout(6, false));

		TableTree tableTree = new TableTree(shlSwtApplication, SWT.BORDER | SWT.FULL_SELECTION);
		tableTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableItem tableItem_3 = new TableItem(tableTree.getTable(), SWT.NONE);
		tableItem_3.setText("New TableItem");

		TableItem tableItem_4 = new TableItem(tableTree.getTable(), SWT.NONE);
		tableItem_4.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
			}
		});
		tableItem_4.setText("New rew");

		TableItem tableItem_5 = new TableItem(tableTree.getTable(), SWT.NONE);
		tableItem_5.setText("New TableItem");

		TableCursor tableCursor_1 = new TableCursor(tableTree.getTable(), SWT.NONE);

		TableColumn tblclmnNewColumn = new TableColumn(tableTree.getTable(), SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");

		TableColumn tblclmnNewColumn_1 = new TableColumn(tableTree.getTable(), SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("New Column");

		TableItem tableItem_6 = new TableItem(tableTree.getTable(), SWT.NONE);
		tableItem_6.setText("New TableItem");

		ToolBar toolBar = new ToolBar(shlSwtApplication, SWT.FLAT | SWT.RIGHT);

		ToolItem tltmplk = new ToolItem(toolBar, SWT.DROP_DOWN);
		tltmplk.setText("\u00FCplk");

		List list = new List(shlSwtApplication, SWT.BORDER);

		CCombo combo = new CCombo(shlSwtApplication, SWT.BORDER);
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		combo.setEditable(false);
		combo.setToolTipText("colors");
		combo.setText("Colors");
		combo.add("TEST");
		Label label = new Label(shlSwtApplication, SWT.SEPARATOR | SWT.VERTICAL);

		table = new Table(shlSwtApplication, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("1");

		TableColumn tblclmnDsf = new TableColumn(table, SWT.ARROW);
		tblclmnDsf.setWidth(100);
		tblclmnDsf.setText("dsf");

		TableCursor tableCursor = new TableCursor(table, SWT.DragDetect);
		tableCursor.setTouchEnabled(true);

		TableItem tableItem_2 = new TableItem(table, SWT.DRAG);
		tableItem_2.setText("2");

		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("New TableItem");

	}
}
