package com.mgiandia.library.ui.reservation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mgiandia.library.dao.BorrowerDAO;
import com.mgiandia.library.dao.Initializer;
import com.mgiandia.library.domain.Book;
import com.mgiandia.library.domain.Borrower;
import com.mgiandia.library.memorydao.BookDAOMemory;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.MemoryInitializer;
import com.mgiandia.library.ui.DefaultJFrame;

@SuppressWarnings("serial")
public class ReservationJFrame extends DefaultJFrame {

	private JPanel contentPane;
	private JTextField borrowerFirstName;
	private JTextField borrowerLastName;

	private JTextField borrowerNo;
	private JLabel borrowerNoLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;

	private JTextField bookTitle;
	private JTextField bookNumber;

	private JLabel bookNoLabel;
	private JLabel titleLabel;

	private JButton searchBook;
	private JButton searchBorrower;
	private JButton reserveButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Initializer initializer = new MemoryInitializer();
					initializer.prepareData();

					ReservationJFrame frame = new ReservationJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReservationJFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		borrowerFirstName = new JTextField();

		borrowerLastName = new JTextField();

		bookNumber = new JTextField();

		borrowerNo = new JTextField();

		bookTitle = new JTextField();

		borrowerNoLabel = new JLabel();
		borrowerNoLabel.setText("Borrower No");

		lastNameLabel = new JLabel();
		lastNameLabel.setText("Last Name");

		firstNameLabel = new JLabel();
		firstNameLabel.setText("First Name");

		bookNoLabel = new JLabel();
		bookNoLabel.setText("Book ISBN");

		titleLabel = new JLabel();
		titleLabel.setText("Book Title");

		searchBook = new JButton();
		searchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBookActionPerformed(e);
			}
		});
		searchBook.setText("Search");

		searchBorrower = new JButton();
		searchBorrower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBorrowerActionPerformed(e);
			}
		});
		searchBorrower.setText("Search");

		reserveButton = new JButton();
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserveButtonActionPerformed(e);
			}
		});
		reserveButton.setText("Reserve");

		setReserveActionEnabled(false);

		cancelButton = new JButton();
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		cancelButton.setText("Cancel");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(31)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(borrowerNoLabel, GroupLayout.PREFERRED_SIZE, 94,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE,
										94, GroupLayout.PREFERRED_SIZE)
								.addComponent(bookNoLabel, GroupLayout.PREFERRED_SIZE, 94,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(borrowerNo, GroupLayout.PREFERRED_SIZE, 62,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6).addComponent(searchBorrower, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(borrowerLastName, GroupLayout.PREFERRED_SIZE, 214,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(borrowerFirstName, GroupLayout.PREFERRED_SIZE, 214,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(bookNumber, GroupLayout.PREFERRED_SIZE, 62,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6).addComponent(searchBook))
								.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(17)
										.addComponent(reserveButton, GroupLayout.PREFERRED_SIZE, 106,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(46, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(24)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(1).addComponent(
												borrowerNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(searchBorrower))
								.addGap(6)
								.addComponent(borrowerLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(borrowerFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(1).addComponent(
												bookNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(searchBook))
								.addGap(11)
								.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(cancelButton).addComponent(reserveButton)))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(borrowerNoLabel)
										.addGap(15).addComponent(lastNameLabel).addGap(10).addComponent(firstNameLabel)
										.addGap(31).addComponent(bookNoLabel).addGap(20).addComponent(titleLabel)))
						.addContainerGap(30, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public int getBorrowerNo() {
		return Integer.parseInt(borrowerNo.getText());
	}

	public String getBookISBN() {
		return bookNumber.getText();
	}

	public boolean isReserveActionEnabled() {
		return reserveButton.isEnabled();
	}

	public void setReserveActionEnabled(boolean enabled) {
		reserveButton.setEnabled(enabled);
	}

	public void setBookTitle(String name) {
		bookTitle.setText(name);
	}

	public void setBorrowerFirstName(String name) {
		borrowerFirstName.setText(name);

	}

	public void setBorrowerLastName(String name) {
		borrowerLastName.setText(name);
	}

	private Borrower borrower;
	private boolean borrowerFound;

	private void searchBorrowerActionPerformed(ActionEvent evt) {

		Integer borrowerNo = getBorrowerNo();
		// use borrowerNo for searching in the database
		BorrowerDAO borrowerDao = new BorrowerDAOMemory();
		borrower = borrowerDao.find(borrowerNo);

		if (borrower == null) {
			borrowerFound = false;
			setBorrowerFirstName("");
			setBorrowerLastName("");
			showError("Borrower not found");
		} else {
			borrowerFound = true;
			setBorrowerFirstName(borrower.getFirstName());
			setBorrowerLastName(borrower.getLastName());
		}

		if (borrowerFound && bookFound) {
			setReserveActionEnabled(true);
		} else {
			setReserveActionEnabled(false);
		}

	}

	private Book book;
	private boolean bookFound;

	private void searchBookActionPerformed(ActionEvent evt) {

		String isbn = getBookISBN();
		BookDAOMemory bookDaoMemory = new BookDAOMemory();
		book = bookDaoMemory.find(isbn);

		if (book == null) {
			bookFound = false;
			setBookTitle("");
			showError("Book not found");
		} else {
			bookFound = true;
			setBookTitle(book.getTitle());
		}

		if (borrowerFound && bookFound) {
			setReserveActionEnabled(true);
		} else {
			setReserveActionEnabled(false);
		}

	}

	private void reserveButtonActionPerformed(ActionEvent evt) {
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
	}

}