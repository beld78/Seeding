package gui;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class SecondWindow {

	protected Shell shell;
	private Logic logic;
	private LinkedList<Table> tableList = new LinkedList<>();
	private Button swapButton;
	private StyledText resultList;
	private StyledText resultListAsSnake;
	private CCombo colorsCombo;
	private Label yee;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public void start(Logic logic) {
		this.logic = logic;
		try {
			SecondWindow window = new SecondWindow(logic);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SecondWindow(Logic logic) {
		super();
		this.logic = logic;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	private void createContents() {
		shell = new Shell();
		shell.setSize(650, 300);
		shell.setText("Adjust groups");
		shell.setLayout(new GridLayout(logic.amountOfGroups, false));
		System.out.println(logic.amountOfGroups);
		for (int i = 1; i <= logic.amountOfGroups; i++) {

			Label groupLabel = new Label(shell, SWT.NONE);
			groupLabel.setText("Group " + i);
			GridData groupLabelGrid = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
			groupLabel.setLayoutData(groupLabelGrid);
		}

		for (int i = 1; i <= logic.amountOfGroups; i++) {

			Table table = new Table(shell, SWT.MULTI | SWT.BORDER);
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			for (int j = 0; j < logic.personsInGroup; j++) {
				TableItem tableItem_1 = new TableItem(table, SWT.NONE);
				tableItem_1.setText(logic.groupsList.get(i - 1)[j]);
			}
			tableList.add(table);
		}

		swapButton = new Button(shell, SWT.NONE);
		swapButton.setText("Swap");
		swapButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int[] playerIndices = new int[2];
				int[] groupIndices = new int[2];
				int i = 0;
				String toBeSwappedZero = "";
				String toBeSwappedOne = "";
				for (Table table : tableList) {
					if (table.getSelectionCount() == 1) {
						if (toBeSwappedZero == "") {
							groupIndices[0] = i;
							playerIndices[0] = table.getSelectionIndex();
							toBeSwappedZero = table.getSelection()[0].getText();
						} else {
							groupIndices[1] = i;
							playerIndices[1] = table.getSelectionIndex();
							toBeSwappedOne = table.getSelection()[0].getText();
						}
					}
					if (table.getSelectionCount() == 2) {
						playerIndices = table.getSelectionIndices();
						groupIndices[0] = i;
						groupIndices[1] = i;
						toBeSwappedZero = table.getItem(playerIndices[0]).getText();
						toBeSwappedOne = table.getItem(playerIndices[1]).getText();
					}
					i++;
				}
				System.out.println(toBeSwappedOne);
				Color colorZero = tableList.get(groupIndices[0]).getItem(playerIndices[0]).getBackground();
				Color colorOne = tableList.get(groupIndices[1]).getItem(playerIndices[1]).getBackground();
				swap(playerIndices, groupIndices, toBeSwappedZero, toBeSwappedOne, colorZero, colorOne);

				unselectSelection();
			}

			private void swap(int[] playerIndices, int[] groupIndices, String toBeSwappedZero, String toBeSwappedOne,
					Color colorZero, Color colorOne) {
				tableList.get(groupIndices[0]).getItem(playerIndices[0]).setText(toBeSwappedOne);
				tableList.get(groupIndices[1]).getItem(playerIndices[1]).setText(toBeSwappedZero);
				tableList.get(groupIndices[0]).getItem(playerIndices[0]).setBackground(colorOne);
				tableList.get(groupIndices[1]).getItem(playerIndices[1]).setBackground(colorZero);
			}
		});

		Button finishGroupsButton = new Button(shell, SWT.None);
		finishGroupsButton.setText("Finish Groups");

		finishGroupsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String participants = "";
				for (Table table : tableList) {
					TableItem[] tableArray = table.getItems();
					for (TableItem tableItem : tableArray) {
						participants += tableItem.getText();
						participants += "\n";
					}
				}
				resultList.setText(participants);
				resultListAsSnake.setText(saveParticipantsInSnakeForm());
			}
		});

		resultList = new StyledText(shell, SWT.BORDER);
		GridData resultListGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 3);
		resultList.setLayoutData(resultListGrid);
		resultList.setText("Result in group form");
		resultList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int end = resultList.getCharCount();
				resultList.setSelectionRange(0, end);
			}
		});
		resultListAsSnake = new StyledText(shell, SWT.BORDER);
		GridData resultListAsSnakeGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 3);
		resultListAsSnake.setLayoutData(resultListAsSnakeGrid);
		resultListAsSnake.setText("Result in snake form");
		resultListAsSnake.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int end = resultListAsSnake.getCharCount();
				resultListAsSnake.setSelectionRange(0, end);
			}
		});
		colorsCombo = new CCombo(shell, SWT.BORDER);
		// GridData colorsComboGrid = new GridData(SWT.FILL, SWT.FILL, true,
		// true, 1, 1);
		// colorsCombo.setLayoutData(colorsComboGrid);
		colorsCombo.setEditable(false);
		colorsCombo.setText("Colors");
		colorsCombo.add("White");
		colorsCombo.add("Red");
		colorsCombo.add("Blue");
		colorsCombo.add("Yellow");
		colorsCombo.add("Green");
		colorsCombo.add("Magenta");
		colorsCombo.add("Cyan");

		Button colorsButton = new Button(shell, SWT.NONE);
		// colorsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
		// true, 1, 1));
		colorsButton.setText("Set color");

		colorsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String colorString = colorsCombo.getText();
				colorPlayers(colorString);
				unselectSelection();
			}

			private void colorPlayers(String colorString) {
				System.out.println(colorString);
				if (colorString.equals("White")) {
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
							System.out.println(tableItem.getBackground());
						}
					}
				}
				if (colorString.equals("Red")) {
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
							System.out.println(tableItem.getBackground());
						}
					}
				}
				if (colorString.equals("Blue")) {
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
							System.out.println(tableItem.getBackground());
						}
					}
				}
				if (colorString.equals("Yellow")) {
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
							System.out.println(tableItem.getBackground());
						}
					}
				}
				if (colorString.equals("Green")) {
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
							System.out.println(tableItem.getBackground());
						}
					}
				}
				if (colorString.equals("Magenta")) {
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
							System.out.println(tableItem.getBackground());
						}
					}
				}
				if (colorString.equals("Cyan")) {
					yee.setVisible(true);
					for (Table table : tableList) {
						TableItem[] tableItems = table.getSelection();
						for (TableItem tableItem : tableItems) {
							tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
							System.out.println(tableItem.getBackground());
						}
					}
				}
			}
		});
		yee = new Label(shell, SWT.NONE);
		yee.setText("YEE");
		yee.setVisible(false);

		Button adjustToColorButton = new Button(shell, SWT.NONE);
		adjustToColorButton.setText("Adjust to colors");

		adjustToColorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				saveParticipantsInSnakeForm();
				LinkedList<String[]> groups = new LinkedList<>();
				for (Table table : tableList) {
					String[] colorsOfParticipants = new String[logic.personsInGroup];
					TableItem[] tableItems = table.getItems();
					for (int i = 0; i < tableItems.length; i++) {
						if (tableItems[i].getBackground().toString().equals("Color {255, 255, 255, 255}")) {
							colorsOfParticipants[i] = ("" + numberInsteadOfColor);
							numberInsteadOfColor++;
						} else {
							colorsOfParticipants[i] = tableItems[i].getBackground().toString();
						}
						System.out.println(colorsOfParticipants[i]);
					}
					groups.add(colorsOfParticipants);
				}
				for (int i = 0; i < groups.size(); i++) {
					Set<String> oneGroup = new HashSet<>();
					for (int j = groups.get(i).length - 1; j >= 0; j--) {
						// duplicate in group check
						if (!oneGroup.add(groups.get(i)[j])) {
							String toBeChanged = groups.get(i)[j].toString();
							if ((i + 1) < groups.size()) {
								// set bool for check if seed player in this or
								// different group
								boolean nextGroupCanBeSwapped = true;
								for (int k = 0; k < groups.get(j).length; k++) {
									if (toBeChanged.equals(groups.get(i + 1)[k].toString())) {
										// seed player in different group
										nextGroupCanBeSwapped = false;
									}
								}
								// seed player in this group
								if (nextGroupCanBeSwapped) {
									seedDown(i, j);
								} else if (i != 0 && !nextGroupCanBeSwapped) {
									seedUp(i, j);
								}
							} else if (i + 1 == groups.size()) {
								seedUp(i, j);
							}
						}
					}
				}
			}

			private void seedUp(int i, int j) {
				String switchInOriginalSpot = tableList.get(i).getItem(j).getText();
				String switchInNewSpot = tableList.get(i - 1).getItem(j).getText();
				Color bgOriginal = tableList.get(i).getItem(j).getBackground();
				Color bgNewSpot = tableList.get(i - 1).getItem(j).getBackground();
				tableList.get(i).getItem(j).setText(switchInNewSpot);
				tableList.get(i).getItem(j).setBackground(bgNewSpot);
				tableList.get(i - 1).getItem(j).setText(switchInOriginalSpot);
				tableList.get(i - 1).getItem(j).setBackground(bgOriginal);
			}

			private void seedDown(int i, int j) {
				String switchInOriginalSpot = tableList.get(i + 1).getItem(j).getText();
				String switchInNewSpot = tableList.get(i).getItem(j).getText();
				Color bgOriginal = tableList.get(i + 1).getItem(j).getBackground();
				Color bgNewSpot = tableList.get(i).getItem(j).getBackground();
				tableList.get(i + 1).getItem(j).setText(switchInNewSpot);
				tableList.get(i + 1).getItem(j).setBackground(bgNewSpot);
				tableList.get(i).getItem(j).setText(switchInOriginalSpot);
				tableList.get(i).getItem(j).setBackground(bgOriginal);
			}
		});
	}

	int numberInsteadOfColor;

	private String saveParticipantsInSnakeForm() {
		String participants = "";
		for (int j = 0; j < logic.personsInGroup; j++) {
			if (isEven(j)) {
				for (int i = 0; i < logic.amountOfGroups; i++) {
					participants += tableList.get(i).getItems()[j].getText();
					participants += "\n";
				}
			}
			if (!isEven(j)) {
				for (int i = logic.amountOfGroups - 1; i >= 0; i--) {
					participants += tableList.get(i).getItems()[j].getText();
					participants += "\n";

				}
			}

		}
		return participants;
	}

	private void unselectSelection() {
		for (Table table : tableList) {
			table.deselectAll();
		}
	}

	private boolean isEven(int i) {
		while (i >= 0) {
			i = i - 2;
		}
		if (i == -2) {
			return true;
		}
		return false;
	}
}
